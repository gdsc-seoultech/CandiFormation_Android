package com.solution_challenge.candiformation.api

import com.solution_challenge.candiformation.models.*
import retrofit2.http.*

interface ApiInterface {

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

    // 좋아요 버튼 클릭
    @PUT("api/articles")
    suspend fun like(
        @Body body: LikeBody,
        @HeaderMap header: Map<String, String>
    ): LikeResponse

    // 댓글 달기
    @POST("api/comments")
    suspend fun writeComment(
        @Body body: CommentBody,
        @HeaderMap header: Map<String, String>
    ): LikeResponse

    // 유저가 좋아한 기사가 뭘까요?
    @GET("api/articles/likes/{username}")
    suspend fun whatArticleLiked(
        @Path("username") username: String,
        @HeaderMap header: Map<String, String>
    ): WhatArticleLikeResponse

    // 선택된 기사에 대한 모든 댓글 가져오기
    @GET("api/comments/{articleId}")
    suspend fun getSelectedArticleComments(
        @Path("articleId") articleId: Int
    ): List<CommentResponse>

    // 댓글 삭제
    @DELETE("api/comments/{nickname}/{articleId}/{commentId}")
    suspend fun deleteComment(
        @Path("nickname") nickname: String,
        @Path("articleId") articleId: Int,
        @Path("commentId") commentId: Int,
        @HeaderMap header: Map<String, String>
    )

    // 이메일 인증을 위한 POST
    @POST("api/authenticate/mail/{mail}")
    suspend fun emailAuth(
        @Path("mail") email: String
    )

    // 코드 인증
    @POST("api/authenticate/verifyCode/{email}/{code}")
    suspend fun verifyCode(
        @Path("email") email: String,
        @Path("code") code: String
    ): Boolean


    // 모든 댓글 가져오기
    @GET("api/comments/users/{username}")
    suspend fun getAllComments(
        @Path("username") username: String,
        @HeaderMap header: Map<String, String>
    ): List<CommentResponse>


    // 한 기사에 대한 좋아요 수
    @GET("api/articles/{articleId}/likes")
    suspend fun getArticleLikes(
        @Path("articleId") articleId: Int
    ): ArticleLikeNumResponse


    // 이메일 중복체크
    @POST("api/users/duplication/{email}")
    suspend fun checkEmailDuplication(
        @Path("email") email: String
    ): Boolean


    // 닉네임 중복체크 이지만 사실은 회원가입
    @POST("api/users/duplication/{email}/{password}/{nickname}")
    suspend fun checkNicknameDuplication(
        @Path("email") email: String,
        @Path("password") password: String,
        @Path("nickname") nickname: String
    ): Boolean
}