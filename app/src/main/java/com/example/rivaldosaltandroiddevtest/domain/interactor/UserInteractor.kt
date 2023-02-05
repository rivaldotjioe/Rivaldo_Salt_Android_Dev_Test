package com.example.rivaldosaltandroiddevtest.domain.interactor

import com.example.rivaldosaltandroiddevtest.data.remote.dto.ResponseGetUsersDetail
import com.example.rivaldosaltandroiddevtest.domain.Resource
import com.example.rivaldosaltandroiddevtest.domain.repointerface.IUserRepository
import com.example.rivaldosaltandroiddevtest.domain.useCase.UserUseCase
import kotlinx.coroutines.flow.Flow

class UserInteractor(val repository: IUserRepository) : UserUseCase {
    override suspend fun getUserById(id: Int): Flow<Resource<ResponseGetUsersDetail>> {
        return repository.getUserById(id)
    }

}