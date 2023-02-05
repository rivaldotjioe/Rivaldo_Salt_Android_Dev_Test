package com.example.rivaldosaltandroiddevtest

import android.app.Application
import com.example.rivaldosaltandroiddevtest.di.interactorModule
import com.example.rivaldosaltandroiddevtest.di.networkModule
import com.example.rivaldosaltandroiddevtest.di.repositoryModule
import com.example.rivaldosaltandroiddevtest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RivaldoSaltAndroidDevTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@RivaldoSaltAndroidDevTestApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    interactorModule,
                    viewModelModule
                )
            )
        }
    }
}