package com.solution_challenge.candiformation.data.repositories

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.solution_challenge.candiformation.api.ApiInterface
import com.solution_challenge.candiformation.models.*
import com.solution_challenge.candiformation.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CandiRepository @Inject constructor(
    private val articleApi: ApiInterface,
    private val context: Context
) {

    private val userSharedPref: SharedPreferences = context.getSharedPreferences(
        "user",
        Context.MODE_PRIVATE
    )

    private val tokenSharedPref: SharedPreferences = context.getSharedPreferences(
        "token",
        Context.MODE_PRIVATE
    )

    suspend fun loginRefresh(): SignUpBody {
        val user = getSavedUser()

        if (user.username != "") {
            saveUser(user)
            Log.d("suee97", "저장된 유저 정보 >> ${user.toString()}")
            Log.d("suee97", "토큰 정보 >> ${getSavedToken().toString()}")
            return user
        }
        Log.d("suee97", "저장된 유저 정보가 없거나 문제가 있음")
        return user
    }


    // 전체 뉴스기사 정보 불러오기 ================================================================
    suspend fun getArticleResponse(): Resource<List<ArticleResponse>> {
        val res = try{
            Resource.Success(articleApi.getArticle().asReversed())
        } catch (e: Exception) {
            Resource.Error("Get Article Response Error")
        }
        return res
    }
    // ==========================================================================================


    // 회원가입
    suspend fun signUp(
        signUpBody: SignUpBody
    ): Pair<String, Boolean> {
        val res = try {
            articleApi.signUp(signUpBody)
        } catch (e: Exception) {
            Log.d("suee97", "sign up error(repo) >>> ${e.localizedMessage}")
            return Pair(e.toString(), false)
        }
        Log.d("suee97", "sign up error(repo) >>> ${res.message} // ${res.status}")

        return Pair(res.status.toString(), true)
    }

    // 로그인 ====================================================================================
    suspend fun signIn(
        loginBody: LoginBody
    ): Pair<String, Boolean> {
        val signInRes = try {
            articleApi.signIn(loginBody)
        } catch (e: Exception) {
            Log.d("suee97", "sign in error(repository) >>> $e")
            return Pair(e.toString(), false)
        }
        tokenSharedPref.edit {
            putString("TOKEN", signInRes.token)
            putString("USERNAME", signInRes.username)
            putString("NICKNAME", signInRes.usernickname)
            commit()
        }
        userSharedPref.edit {
            putString("UN", signInRes.username)
            putString("PW", loginBody.password)
            putString("NN", signInRes.usernickname)
            commit()
        }
        return Pair(signInRes.usernickname, true)
    }
    // ========================================================================================


    // 좋아요 클릭
    suspend fun like(
        likeBody: LikeBody
    ) {
        val likeRes = try {
            articleApi.like(likeBody, getHeaderMap())
        } catch (e: Exception) {
            Log.d("suee97", "$e")
        }
    }


    fun saveUser(signUpBody: SignUpBody) {
        userSharedPref.edit {
            putString("UN", signUpBody.username)
            putString("PW", signUpBody.password)
            putString("NN", signUpBody.nickname)
            commit()
        }
        Log.d("login shared", signUpBody.toString())
    }

    private fun deleteUser() {
        userSharedPref.edit {
            putString("UN", "")
            putString("PW", "")
            putString("NN", "")
            commit()
        }
    }

    fun getSavedUser() = SignUpBody(
        userSharedPref.getString("UN", "") ?: "",
        userSharedPref.getString("PW", "") ?: "",
        userSharedPref.getString("NN", "") ?: "",
    )

    fun saveToken(loginResponse: LoginResponse) {
        tokenSharedPref.edit {
            putString("TOKEN", loginResponse.token)
            putString("USERNAME", loginResponse.username)
            putString("NICKNAME", loginResponse.usernickname)
            commit()
        }
    }

    private fun deleteToken() {
        tokenSharedPref.edit {
            putString("TOKEN", "")
            putString("USERNAME", "")
            putString("NICKNAME", "")
            commit()
        }
    }

    // 토큰 =============================================================================
    fun getHeaderMap() = mapOf(
        pair = Pair(
            "Authorization",
            "Bearer " + tokenSharedPref.getString("TOKEN", null) ?: ""
        )
    )

    fun getSavedToken(): Pair<String, String> {
        return Pair("Authorization", "Bearer " + tokenSharedPref.getString("TOKEN", null) ?: "")
    }


    // 로그아웃 ====================================================================================
    fun logOut() {
        deleteUser()
        deleteToken()
    }
    // ============================================================================================


    // 댓글쓰기 ==================================================================================
    suspend fun writeComment(commentBody: CommentBody) {
        val commentRes = try {
            articleApi.writeComment(commentBody, getHeaderMap())
        } catch (e: Exception) {
            Log.d("suee97", "write comment error >>> $e")
        }
    }
    // ==========================================================================================


    // 유저가 좋아한 기사 id 불러오기 ==============================================================
    suspend fun whatArticleLiked(username: String): WhatArticleLikeResponse {
        return articleApi.whatArticleLiked(username, getHeaderMap())
    }
    // ========================================================================================


    // 선택된 기사에 대한 모든 댓글 가져오기 =========================================================
    suspend fun getSelectedArticleComments(articleId: Int): List<CommentResponse> {
        return articleApi.getSelectedArticleComments(articleId)
    }
    // ========================================================================================


    // 코멘트 삭제
    suspend fun deleteComment(
        nickname: String,
        articleId: Int,
        commentId: Int
    ) {
        articleApi.deleteComment(nickname, articleId, commentId, getHeaderMap())
    }


    // 이메일 인증(코드보내기)
    suspend fun emailAuth(email: String) {
        articleApi.emailAuth(email)
    }


    // 코드 인증
    suspend fun verifyCode(email: String, code: String): Boolean {
        return articleApi.verifyCode(email, code)
    }


    // 모든 댓글 가져오기
    suspend fun getAllComments(username: String): List<CommentResponse> {
        val res = try {
            articleApi.getAllComments(username = username, header = getHeaderMap())
        } catch (e: Exception) {
            listOf<CommentResponse>()
        }

        return res
    }

    // article id로 like 수 가져오기
    suspend fun getArticleLikes(articleId: Int): ArticleLikeNumResponse {
        return articleApi.getArticleLikes(articleId)
    }

    // 이메일 중복 체크
    suspend fun checkEmailDuplication(email: String): Boolean {
        return articleApi.checkEmailDuplication(email)
    }

    // 닉네임 중복 체크
    suspend fun checkNicknameDuplication(
        email: String,
        password: String,
        nickname: String
    ): Boolean {
        return articleApi.checkNicknameDuplication(email, password, nickname)
    }
}