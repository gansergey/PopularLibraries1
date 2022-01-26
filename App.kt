package com.gaaan.popularlibraries

import android.app.Application
import com.gaaan.popularlibraries.dagger.AppComponent
import com.gaaan.popularlibraries.dagger.DaggerAppComponent
class App : Application() {

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .setContext(this)
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}