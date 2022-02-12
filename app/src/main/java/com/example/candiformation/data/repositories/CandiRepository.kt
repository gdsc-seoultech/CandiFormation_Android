package com.example.candiformation.data.repositories

import android.util.Log
import com.example.candiformation.api.ArticleApiInterface
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.models.LoginBody
import com.example.candiformation.models.SignUpBody
import com.example.candiformation.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CandiRepository @Inject constructor(
    private val articleApi: ArticleApiInterface
) {

    // 기사 불러오기
    suspend fun getArticleResponse(): Resource<List<ArticleResponse>> {
        val articleRes = try {
            articleApi.getArticle()
        } catch (e: Exception) {
            return Resource.Error("Error : ${e.localizedMessage}")
        }
        return Resource.Success(articleRes)
    }

    // 회원가입
    suspend fun signUp(
        signUpBody: SignUpBody
    ): Boolean {
        val signUpRes = try {
            articleApi.signUp(signUpBody)
        } catch (e: Exception) {
            Log.d("suee97", "$e")
            return false
        }
        if(signUpRes.status == "200") {
            Log.d("suee97", "sign up response status is 200")
            return true
        }
        return false

    }

    // 로그인
    suspend fun signIn(
        loginBody: LoginBody
    ): Pair<String, Boolean> {
        val signInRes = try {
            articleApi.signIn(loginBody)
        } catch (e: Exception) {
            Log.d("suee97", "$e")
            return Pair(e.toString(), false)
        }
        return Pair(signInRes.usernickname, true)
    }
}