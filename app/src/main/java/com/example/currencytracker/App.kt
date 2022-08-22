package com.example.currencytracker

import android.app.Application
import com.example.currencytracker.di.component.AppComponent
import com.example.currencytracker.di.component.DaggerAppComponent
import com.example.currencytracker.feature.settings.SortSettings

class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        lateinit var appComponent: AppComponent
            private set

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }


}