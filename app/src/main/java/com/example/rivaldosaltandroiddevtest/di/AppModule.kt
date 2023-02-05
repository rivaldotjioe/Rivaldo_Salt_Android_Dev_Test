package com.example.rivaldosaltandroiddevtest.di

import com.example.rivaldosaltandroiddevtest.data.remote.RemoteDataSource
import com.example.rivaldosaltandroiddevtest.data.remote.ReqresService
import com.example.rivaldosaltandroiddevtest.data.remote.ReqresServiceImpl
import com.example.rivaldosaltandroiddevtest.data.repository.LoginRepository
import com.example.rivaldosaltandroiddevtest.data.repository.UserRepository
import com.example.rivaldosaltandroiddevtest.domain.interactor.LoginInteractor
import com.example.rivaldosaltandroiddevtest.domain.interactor.UserInteractor
import com.example.rivaldosaltandroiddevtest.domain.repointerface.ILoginRepository
import com.example.rivaldosaltandroiddevtest.domain.repointerface.IUserRepository
import com.example.rivaldosaltandroiddevtest.domain.useCase.LoginUseCase
import com.example.rivaldosaltandroiddevtest.domain.useCase.UserUseCase
import com.example.rivaldosaltandroiddevtest.ui.login.LoginViewModel
import com.example.rivaldosaltandroiddevtest.ui.main.MainViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single<ReqresService> {
        ReqresServiceImpl(
            client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json { ignoreUnknownKeys = true })
                }
            }
        )
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<ILoginRepository> { LoginRepository(get()) }
    single<IUserRepository> { UserRepository(get()) }
}

val interactorModule = module {
    factory<LoginUseCase> {
        LoginInteractor(get())
    }
    factory<UserUseCase> { UserInteractor(get()) }

}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
}