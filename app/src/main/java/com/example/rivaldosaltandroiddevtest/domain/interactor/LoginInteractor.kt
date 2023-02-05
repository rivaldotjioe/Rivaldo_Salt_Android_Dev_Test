package com.example.rivaldosaltandroiddevtest.domain.interactor

import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginResponse
import com.example.rivaldosaltandroiddevtest.domain.Resource
import com.example.rivaldosaltandroiddevtest.domain.repointerface.ILoginRepository
import com.example.rivaldosaltandroiddevtest.domain.useCase.LoginUseCase
import kotlinx.coroutines.flow.Flow

class LoginInteractor(val repository: ILoginRepository) : LoginUseCase{
    override suspend fun login(email: String, password: String): Flow<Resource<LoginResponse>> = repository.login(email, password)
}