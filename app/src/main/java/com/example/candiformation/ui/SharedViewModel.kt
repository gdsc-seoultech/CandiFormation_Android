package com.example.candiformation.ui

import androidx.lifecycle.ViewModel
import com.example.candiformation.data.repositories.CandiRepository
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private val repository: CandiRepository
): ViewModel() {

}