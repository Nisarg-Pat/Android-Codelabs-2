package com.example.a024bookshelf

import android.app.Application
import com.example.a024bookshelf.data.container.AppContainer
import com.example.a024bookshelf.data.container.DefaultAppContainer

class MainApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}