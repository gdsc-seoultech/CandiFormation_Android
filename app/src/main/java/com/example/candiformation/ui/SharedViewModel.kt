package com.example.candiformation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.candiformation.data.repositories.CandiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: CandiRepository
): ViewModel() {

    // Bottom bar shown
    var bottomBarShown = mutableStateOf(false)
}