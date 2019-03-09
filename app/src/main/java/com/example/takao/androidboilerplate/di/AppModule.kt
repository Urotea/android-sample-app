package com.example.takao.androidboilerplate.di

import android.app.Application
import android.content.Context
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.middleware.LogMiddleware
import com.example.takao.androidboilerplate.redux.Middleware
import com.example.takao.androidboilerplate.store.MainActivityState
import com.example.takao.androidboilerplate.store.MainActivityStore
import com.example.takao.androidboilerplate.util.rx.AppSchedulerProvider
import com.example.takao.androidboilerplate.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AppModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    @JvmStatic
    fun provideScheduler(): SchedulerProvider = AppSchedulerProvider()

    @Singleton
    @Provides
    @JvmStatic
    fun provideLogMiddleware(logMiddleware: LogMiddleware): Middleware<MainActivityActions, MainActivityState> =
        logMiddleware
}
