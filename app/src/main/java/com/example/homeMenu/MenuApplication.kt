package com.example.homeMenu

import android.app.Application
import com.example.homeMenu.data.AppContainer
import com.example.homeMenu.data.AppDataContainer

class MenuApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
