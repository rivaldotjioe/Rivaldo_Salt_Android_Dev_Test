package com.example.rivaldosaltandroiddevtest.ui.login

import androidx.lifecycle.ViewModel
import com.example.rivaldosaltandroiddevtest.domain.useCase.LoginUseCase

class LoginViewModel(val useCase: LoginUseCase) : ViewModel() {

    suspend fun login(email: String, password: String) = useCase.login(email, password)
}