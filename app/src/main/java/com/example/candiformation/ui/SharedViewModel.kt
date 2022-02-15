package com.example.candiformation.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.candiformation.data.repositories.CandiRepository
import com.example.candiformation.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
            nickname = "",
            tel = "0000"
        )
    )
    // ==================================================================================

    // Bottom bar shown
    var bottomBarShown = mutableStateOf(false)


    // Article Response ===================================================================
//    var isArticleLoading = mutableStateOf(false)
//    private var _getArticleData: MutableLiveData<List<ArticleResponse>> =
//        MutableLiveData<List<ArticleResponse>>()
//    var getArticleData: LiveData<List<ArticleResponse>> = _getArticleData
//
//    suspend fun getArticleData(): Resource<List<ArticleResponse>> {
//        val result = repository.getArticleResponse()
//        if (result is Resource.Success) {
//            isArticleLoading.value = true
//            _getArticleData.value = result.data!!
//        }
//
//        return result
//    }

    var articleDataList = mutableStateOf(listOf<ArticleResponse>())

    fun getArticle() {
        viewModelScope.launch {
            articleDataList.value = repository.getArticleResponse()
        }
    }

    // ===============================================================================


    // Current Article data ==========================================================
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
            nickname = "",
            tel = "0000"
        )
    )

    fun postLoginBody() {
        viewModelScope.launch {
            if (repository.signUp(signUpBody.value)) {
                Log.d("suee97", "회원가입 성공")
            } else {
                Log.d("suee97", "회원가입 실패")
            }
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
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.signIn(LoginBody(idText, passwordText))

            if (result!!.second) {
                currentUser.value.nickname = result!!.first
                currentUser.value.username = idText
                currentUser.value.password = passwordText
                onSuccess()
            } else {
                onFailure()
            }
        }
    }
    // ==================================================================================


    // Like button click ================================================================
    fun like(
        likeBody: LikeBody
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.like(likeBody = likeBody)
            articleDataList.value = repository.getArticleResponse()
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
            nickname = "",
            tel = "0000"
        )
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
        }
    }
}