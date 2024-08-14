package com.example.a023amphibians

import android.app.Application
import com.example.a023amphibians.data.container.AppContainer
import com.example.a023amphibians.data.container.DefaultAppContainer

class MainApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}