package com.example.candiformation.data.repositories

import com.example.candiformation.api.ArticleApiInterface
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CandiRepository @Inject constructor(
    private val articleApi: ArticleApiInterface
) {

    suspend fun getArticleResponse(): Resource<List<ArticleResponse>> {
        val articleRes = try {
            articleApi.getArticle()
        } catch (e: Exception) {
            return Resource.Error("Error : ${e.localizedMessage}")
        }
        return Resource.Success(articleRes)
    }

}