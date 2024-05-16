package com.example.tiptime

import android.app.Application
import com.example.tiptime.Data.AppContainer
import com.example.tiptime.Data.AppDataContainer


class InventoryApplication: Application() {
    lateinit var container: AppContainer


    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}