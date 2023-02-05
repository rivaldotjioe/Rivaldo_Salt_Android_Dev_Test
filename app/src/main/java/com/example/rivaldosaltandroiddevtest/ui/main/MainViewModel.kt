package com.example.rivaldosaltandroiddevtest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rivaldosaltandroiddevtest.data.remote.dto.DataUser
import com.example.rivaldosaltandroiddevtest.data.remote.dto.ResponseGetUsersDetail
import com.example.rivaldosaltandroiddevtest.domain.Resource
import com.example.rivaldosaltandroiddevtest.domain.useCase.UserUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class MainViewModel(val userUseCase: UserUseCase) : ViewModel() {
    private val _dataUser = MutableStateFlow<Resource<ResponseGetUsersDetail>>(Resource.Loading())
    val dataUser: StateFlow<Resource<ResponseGetUsersDetail>> = _dataUser

   suspend fun getUserDetailById(id: Int) {
        userUseCase.getUserById(id).collect {
            _dataUser.value = it
        }
    }

}