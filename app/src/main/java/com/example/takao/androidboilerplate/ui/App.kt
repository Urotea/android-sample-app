package com.example.takao.androidboilerplate.ui

import android.content.Context
import com.example.takao.androidboilerplate.BuildConfig
import com.example.takao.androidboilerplate.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

open class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        this.setupTimber()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    private fun setupTimber() {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
