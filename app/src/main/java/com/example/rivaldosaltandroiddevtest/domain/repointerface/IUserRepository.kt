package com.example.rivaldosaltandroiddevtest.domain.repointerface

import com.example.rivaldosaltandroiddevtest.data.remote.dto.ResponseGetUsersDetail
import com.example.rivaldosaltandroiddevtest.domain.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUserById(id: Int): Flow<Resource<ResponseGetUsersDetail>>
}