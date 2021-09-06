package com.taufik.tixidgithubuser.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.taufik.tixidgithubuser.data.repository.TixIdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TixIdViewModel @Inject constructor(
    repository: TixIdRepository
): ViewModel() {
    val data = repository.getAllUsers().cachedIn(viewModelScope)
}