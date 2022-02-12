package com.example.candiformation.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.candiformation.data.repositories.CandiRepository
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.models.LoginBody
import com.example.candiformation.models.SignUpBody
import com.example.candiformation.utils.Resource
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

    // Bottom bar shown
    var bottomBarShown = mutableStateOf(false)


    // Article ======================================================================
    var isArticleLoading = mutableStateOf(false)
    private var _getArticleData: MutableLiveData<List<ArticleResponse>> =
        MutableLiveData<List<ArticleResponse>>()
    var getArticleData: LiveData<List<ArticleResponse>> = _getArticleData

    suspend fun getArticleData(): Resource<List<ArticleResponse>> {
        val result = repository.getArticleResponse()
        if (result is Resource.Success) {
            isArticleLoading.value = true
            _getArticleData.value = result.data!!
        }

        return result
    }
    // ===============================================================================


    // Current Article data ==========================================================
    var articleId = mutableStateOf(-1)
    var articleTitle = mutableStateOf("")
    var articleContent = mutableStateOf("")
    var articleAgency = mutableStateOf("")
    var articleLink = mutableStateOf("")
    var articleImage = mutableStateOf("")
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


    // Current User Info ================================================================
    var tempUser = mutableStateOf(
        LoginBody(
            username = "",
            password = ""
        )
    )

    var currentUser = mutableStateOf(
        SignUpBody(
            username = "",
            password = "",
            nickname = "",
            tel = "0000"
        )
    )
    // ==================================================================================



    // SignIn ===========================================================================
    fun login(
        idText: String,
        passwordText: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            currentUser.value.nickname = repository.signIn(LoginBody(idText, passwordText))
            currentUser.value.username = idText
            currentUser.value.password = passwordText
        }
    }
    // ==================================================================================
}