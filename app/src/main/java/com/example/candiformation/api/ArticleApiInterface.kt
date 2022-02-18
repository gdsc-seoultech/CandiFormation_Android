package com.example.candiformation.api

import com.example.candiformation.models.*
import retrofit2.Call
import retrofit2.http.*

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

    @DELETE("api/comments/{nickname}/{articleId}/{commentId}")
    suspend fun deleteComment(
        @Path("nickname") nickname: String,
        @Path("articleId") articleId: Int,
        @Path("commentId") commentId: Int,
        @HeaderMap header: Map<String, String>
    )
}