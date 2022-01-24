package com.example.candiformation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.candiformation.data.repositories.CandiRepository
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: CandiRepository
): ViewModel() {

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


    // Current Article data
    var articleId = mutableStateOf(-1)
    var articleTitle = mutableStateOf("")
    var articleContent = mutableStateOf("")


}