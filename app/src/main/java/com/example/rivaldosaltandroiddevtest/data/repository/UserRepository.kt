package com.example.rivaldosaltandroiddevtest.data.repository

import com.example.rivaldosaltandroiddevtest.data.remote.ApiResponse
import com.example.rivaldosaltandroiddevtest.data.remote.RemoteDataSource
import com.example.rivaldosaltandroiddevtest.data.remote.dto.ResponseGetUsersDetail
import com.example.rivaldosaltandroiddevtest.domain.Resource
import com.example.rivaldosaltandroiddevtest.domain.repointerface.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(val remoteDataSource: RemoteDataSource) : IUserRepository {
    override suspend fun getUserById(id: Int): Flow<Resource<ResponseGetUsersDetail>> {
        return flow {
            remoteDataSource.getUserById(id = id).collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(data = response.data as ResponseGetUsersDetail))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(message = response.message.toString()))
                    }
                    is ApiResponse.Loading -> {

                    }
                }
            }
        }
    }


}