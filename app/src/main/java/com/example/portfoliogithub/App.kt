package com.example.portfoliogithub

import android.app.Application
import com.example.portfoliogithub.data.di.DataModule
import com.example.portfoliogithub.domain.di.DomainModule
import com.example.portfoliogithub.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}