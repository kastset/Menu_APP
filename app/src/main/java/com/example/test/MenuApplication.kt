package com.example.test

import android.app.Application
import com.example.test.data.AppContainer
import com.example.test.data.AppDataContainer

class MenuApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
