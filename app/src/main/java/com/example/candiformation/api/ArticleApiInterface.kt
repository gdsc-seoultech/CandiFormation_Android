package com.example.candiformation.api

import com.example.candiformation.models.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ArticleApiInterface {

    // 모든 article 불러오기
    @GET("api/articles")
    suspend fun getArticle(): List<ArticleResponse> // Article list를 return


    // 회원가입
    @POST("api/users")
    suspend fun signUp(
        @Body body: SignUpBody
    ): SignUpResponse

    // 로그인
    @POST("api/users/login")
    suspend fun signIn(
        @Body body: LoginBody
    ): LoginResponse

}