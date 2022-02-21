package com.example.candiformation.ui

import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.candiformation.data.repositories.CandiRepository
import com.example.candiformation.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: CandiRepository
) : ViewModel() {

    // 로그인 정보 ======================================================================
    var currentUser = mutableStateOf(
        SignUpBody(
            username = "",
            password = "",
            nickname = ""
        )
    )
    // ==================================================================================

    // 바텀 바 show boolean ==================================================================
    var bottomBarShown = mutableStateOf(false)
    // ======================================================================================


    // 모든 기사 정보 불러오기 ===================================================================
    val articleDataList: MutableLiveData<List<ArticleResponse>> =
        MutableLiveData(listOf<ArticleResponse>())

    fun getArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            articleDataList.postValue(repository.getArticleResponse())
        }
    }
    // =======================================================================================


    // Current Article data =================================================================
    var articleId = mutableStateOf(-1)
    var articleTitle = mutableStateOf("")
    var articleContent = mutableStateOf("")
    var articleAgency = mutableStateOf("")
    var articleLink = mutableStateOf("")
    var articleImage = mutableStateOf("")
    var articleLikeNum = mutableStateOf(0)
    var articleCommentNum = mutableStateOf(0)
    // ===============================================================================


    // Time Calculate ================================================================
    fun getLeftBonTime(): Long {
        val dateFormat = SimpleDateFormat("yyyyMMdd")
        val endDate = dateFormat.parse("20220309").time
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time.time

        return (endDate - today) / (24 * 60 * 60 * 1000)
    }

    fun getLeftSazunTime(): Long {
        val dateFormat = SimpleDateFormat("yyyyMMdd")
        val endDate = dateFormat.parse("20220304").time
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time.time

        return (endDate - today) / (24 * 60 * 60 * 1000)
    }
    // ===============================================================================


    // SignUP ===========================================================================
    var signUpBody = mutableStateOf(
        SignUpBody(
            username = "",
            password = "",
            nickname = ""
        )
    )

    fun signUp() {
        viewModelScope.launch {
            repository.signUp(signUpBody.value)
        }
    }
    // ==================================================================================


    // SignIn ===========================================================================
    fun login(
        idText: String,
        passwordText: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        viewModelScope.launch() {
            val result = repository.signIn(LoginBody(idText, passwordText))

            if (result!!.second) {
                currentUser.value.nickname = result!!.first
                currentUser.value.username = idText
                currentUser.value.password = passwordText
                onSuccess()
                Log.d("suee97", "로그인성공 >>> ${result!!.second}")
            } else {
                onFailure()
                Log.d("suee97", "로그인에러 >>> ${result!!.second}")
            }
        }
    }
    // ==================================================================================


    // Like button click ================================================================
    fun like(
        likeBody: LikeBody
    ) {
        viewModelScope.launch() {
            repository.like(likeBody = likeBody)
            articleDataList.postValue(repository.getArticleResponse())
            whatArticleLiked.value = repository.whatArticleLiked(likeBody.username)
        }
    }
    // ==================================================================================


    // Login Refresh ====================================================================
    fun loginRefresh() {
        viewModelScope.launch {
            val tempUser = repository.loginRefresh()
            currentUser.value.username = tempUser.username
            currentUser.value.nickname = tempUser.nickname
            currentUser.value.password = tempUser.password
        }
    }
    // ==================================================================================


    // Logout ===========================================================================
    fun logOut() {
        repository.logOut()
        currentUser.value = SignUpBody(
            username = "",
            password = "",
            nickname = ""
        )
        whatArticleLiked.value.articles = listOf()
        whatArticleLiked.value.username = ""
    }
    // ==================================================================================


    // 댓글쓰기 =============================================================================
    var currentCommentBody = mutableStateOf(
        CommentBody(
            articleId = (-1),
            nickname = "",
            isSecret = true,
            content = "",
            createdAt = ""
        )
    )

    fun writeComment(commentBody: CommentBody) {
        viewModelScope.launch {
            repository.writeComment(commentBody)
            selectedArticleComments.postValue(repository.getSelectedArticleComments(articleId.value))
        }
    }
    // ===================================================================================


    // 유저가 좋아한 기사 id 불러오기 ==============================================================
    var whatArticleLiked = mutableStateOf(WhatArticleLikeResponse(listOf(), ""))

    fun whatArticleLiked(username: String) {
        viewModelScope.launch {
            whatArticleLiked.value = repository.whatArticleLiked(username)
        }
    }
    // =======================================================================================


    //선택된 기사에 대한 모든 댓글 가져오기 =========================================================
    var selectedArticleComments: MutableLiveData<List<CommentResponse>> =
        MutableLiveData(listOf())

    fun getSelectedArticleComments(articleId: Int) {
        viewModelScope.launch {
            selectedArticleComments.postValue(repository.getSelectedArticleComments(articleId))
        }
    }
    // ========================================================================================


    // 코멘트 삭제 ============================================================================
    fun deleteComment(commentId: Int) {
        viewModelScope.launch {
            repository.deleteComment(currentUser.value.nickname, articleId.value, commentId)
            selectedArticleComments.postValue(repository.getSelectedArticleComments(articleId.value))
        }
    }
    // ======================================================================================



    // Article Loading
    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun refresh() {
        // This doesn't handle multiple 'refreshing' tasks, don't use this
        viewModelScope.launch {
            // A fake 2 second 'refresh'
            _isRefreshing.emit(true)
            articleDataList.postValue(repository.getArticleResponse())
            _isRefreshing.emit(false)
        }
    }


    // 이메일 인증
    var tempEmail = mutableStateOf("")

    fun emailAuth(email: String) {
        viewModelScope.launch {
            repository.emailAuth(email)
        }
    }
}