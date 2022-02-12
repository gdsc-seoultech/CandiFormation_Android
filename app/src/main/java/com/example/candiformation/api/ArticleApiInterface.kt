package com.example.candiformation.api

import com.example.candiformation.models.ArticleResponse
import retrofit2.http.GET

interface ArticleApiInterface {

    @GET("api/articles")
    suspend fun getArticle(): List<ArticleResponse> // Article list를 return

}