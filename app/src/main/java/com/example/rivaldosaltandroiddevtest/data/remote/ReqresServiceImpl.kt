package com.example.rivaldosaltandroiddevtest.data.remote

import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginRequest
import com.example.rivaldosaltandroiddevtest.data.remote.dto.LoginResponse
import com.example.rivaldosaltandroiddevtest.data.remote.dto.ResponseGetUsersDetail
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class ReqresServiceImpl(private val client: HttpClient) : ReqresService {
    override suspend fun login(loginRequest: LoginRequest): ApiResponse<LoginResponse?> {
        return try {
           ApiResponse.Success(data = client.post {
               url(HttpRoutes.LOGIN)
               contentType(ContentType.Application.Json)
               body = loginRequest

           })
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            ApiResponse.Error(e.response.status.description)
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            ApiResponse.Error(e.response.status.description)
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            ApiResponse.Error(e.response.status.description)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ApiResponse.Error(e.message ?: "Error Occurred!")
        }
    }

    override suspend fun getUserDetail(id: Int): ApiResponse<ResponseGetUsersDetail> {
        return try {
            ApiResponse.Success(data = client.get {
                url(HttpRoutes.GET_USER+id.toString())
                contentType(ContentType.Application.Json)

            })
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            ApiResponse.Error(e.response.status.description)
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            ApiResponse.Error(e.response.status.description)
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            ApiResponse.Error(e.response.status.description)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ApiResponse.Error(e.message ?: "Error Occurred!")
        }
    }

}