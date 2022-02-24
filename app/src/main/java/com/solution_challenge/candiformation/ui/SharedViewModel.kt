package com.solution_challenge.candiformation.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solution_challenge.candiformation.data.repositories.CandiRepository
import com.solution_challenge.candiformation.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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


    // 모든 기사 정보 불러오기 + 메인 5개 =========================================================
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
    var articleDateTime = mutableStateOf("")
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

    fun signUp(): String {
        var res = mutableStateOf("")

        viewModelScope.launch {
            res.value = repository.signUp(signUpBody.value).first
        }

        return res.value
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
            getArticleLikes.value = repository.getArticleLikes(articleId.value).likes
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun writeComment(commentBody: CommentBody) {
        val formatterDate = DateTimeFormatter.ISO_DATE
        val formatterTime = DateTimeFormatter.ISO_LOCAL_TIME

        viewModelScope.launch {
            val date = LocalDateTime.now().format(formatterDate).slice(5..9)
            val time = LocalDateTime.now().format(formatterTime).slice(0..4)
            currentCommentBody.value.createdAt = "${date} ${time}"
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


    // 선택된 기사에 대한 모든 댓글 가져오기 =========================================================
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
            getArticle()
            delay(1000)
            _isRefreshing.emit(false)
        }
    }


    // 이메일 인증
    var authEmail = mutableStateOf("")

    fun emailAuth(email: String) {
        viewModelScope.launch {
            repository.emailAuth(email)
        }
    }

    val isVerified: MutableLiveData<VerifyResponse> = MutableLiveData()

    fun verifyCode(tempCode: String) {
        viewModelScope.launch {
            val res = mutableStateOf(VerifyResponse("", false))
            res.value = repository.verifyCode(authEmail.value, tempCode)
            isVerified.value = res.value
        }
    }

    // 구글 signUp + Login
    fun googleLogin(
        idText: String,
        passwordText: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        viewModelScope.launch {
            repository.signUp(signUpBody.value)
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

    // 모든 댓글 가져오기
    val userComments: MutableLiveData<List<CommentResponse>> = MutableLiveData()

    fun getAllComments(username: String) {
        viewModelScope.launch {
            userComments.value = repository.getAllComments(username)
        }
    }

    // article id로 like 수 가져오기
    var getArticleLikes = mutableStateOf(0)

    fun getArticleLikes(_articleId: Int) {
        viewModelScope.launch {
            getArticleLikes.value = repository.getArticleLikes(_articleId).likes
        }
    }
}