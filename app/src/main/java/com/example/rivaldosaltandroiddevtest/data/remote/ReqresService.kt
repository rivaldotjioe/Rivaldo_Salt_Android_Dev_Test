package com.example.rivaldosaltandroiddevtest.data.remote

import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginRequest
import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginResponse
import com.example.rivaldosaltandroiddevtest.data.remote.dto.ResponseGetUsersDetail
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface ReqresService {

    suspend fun login(loginRequest: LoginRequest): ApiResponse<LoginResponse?>

    suspend fun getUserDetail(id: Int): ApiResponse<ResponseGetUsersDetail>

    companion object {
        fun create(): ReqresService {
            return ReqresServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }

}