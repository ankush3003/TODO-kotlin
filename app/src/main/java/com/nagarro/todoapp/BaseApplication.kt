package com.nagarro.todoapp

import android.app.Application
import org.koin.android.ext.android.startKoin

class BaseApplication: Application() {
//Q: How to create Application class as singleton
    override fun onCreate() {
        super.onCreate()
        startKoin(this,
            listOf(koinModule))
    }
}