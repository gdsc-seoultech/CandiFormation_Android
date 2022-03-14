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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
            articleDataList.postValue(repository.getArticleResponse().data)
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
            articleDataList.postValue(repository.getArticleResponse().data)
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
            userComments.value = repository.getAllComments(currentUser.value.username)
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

    // 유저가 작성한 모든 댓글 가져오기
    val userComments: MutableLiveData<List<CommentResponse>> = MutableLiveData(listOf())

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


    // 이메일 중복체크 ==========================================================================
    val signUpMsg = MutableLiveData<String?>("")
    val signUpNextButtonEnabled = MutableLiveData<Boolean>(false)
    val signUpTextFieldEnabled = MutableLiveData<Boolean>(true)
    val serviceUsageCheck = MutableLiveData<Boolean>(false)
    val privateInfoCheck = MutableLiveData<Boolean>(false)

    fun setSignUpMsg(msg: String) {
        signUpMsg.postValue(msg)
    }

    fun setSignUpInitEnabled() {
        signUpNextButtonEnabled.postValue(false)
        signUpTextFieldEnabled.postValue(true)
    }

    fun setServiceUsageCheck() {
        if(serviceUsageCheck.value == false) {
            serviceUsageCheck.postValue(true)
        } else {
            serviceUsageCheck.postValue(false)
        }
    }

    fun setPrivateInfoCheck() {
        if(privateInfoCheck.value == false) {
            privateInfoCheck.postValue(true)
        } else {
            privateInfoCheck.postValue(false)
        }
    }

    fun checkEmailDuplication(email: String) {
        viewModelScope.launch {
            val res = repository.checkEmailDuplication(email)
            if(res == false) {
                signUpMsg.postValue("중복된 이메일이 존재합니다.")
            } else if(res == true && serviceUsageCheck.value == true && privateInfoCheck.value == true) {
                signUpNextButtonEnabled.postValue(res)
                signUpTextFieldEnabled.postValue(!res)
                signUpMsg.postValue("다음을 눌러서 진행해주세요!")
            }
        }
    }
    // =====================================================================================


    // 인증코드확인 ==========================================================================
    var authEmail = mutableStateOf("")

    fun emailAuth(email: String) {
        viewModelScope.launch {
            repository.emailAuth(email)
        }
    }

    val authMsg = MutableLiveData<String?>("")

    fun setAuthMsg(msg: String) {
        authMsg.postValue(msg)
    }

    val isVerified = MutableLiveData<Boolean>(false)
    val authNextButtonEnabled = MutableLiveData<Boolean>(false)
    val authTextFieldEnabled = MutableLiveData<Boolean>(true)

    fun setAuthInitEnabled() {
        authNextButtonEnabled.postValue(false)
        authTextFieldEnabled.postValue(true)
    }

    fun verifyCode(tempCode: String) {
        viewModelScope.launch {
            val res = repository.verifyCode(authEmail.value, tempCode)
            isVerified.postValue(res)
            if(res == true) {
                authNextButtonEnabled.postValue(true)
                authTextFieldEnabled.postValue(false)
                authMsg.postValue("다음을 눌러서 진행해주세요!")
            } else {
                authMsg.postValue("올바른 인증코드가 아닙니다.")
            }
        }
    }
    //  =====================================================================================


    // 닉네임 중복체크(사실상 회원가입) =========================================================
    val nicknameMsg = MutableLiveData("")

    fun setNicknameMsg(msg: String) {
        nicknameMsg.postValue(msg)
    }

    val checkNicknameDuplicationRes = MutableLiveData<Boolean>(false)

    fun checkNicknameDuplication(
        email: String,
        password: String,
        nickname: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        viewModelScope.launch {
            val res = repository.checkNicknameDuplication(email, password, nickname)
            checkNicknameDuplicationRes.postValue(res)
            if(res == true) {
                onSuccess()
            } else {
                onFailure()
            }
        }
    }
    // =====================================================================================
}