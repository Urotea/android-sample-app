package com.example.takao.androidboilerplate.di

import android.app.Application
import android.content.Context
import com.example.takao.androidboilerplate.dao.NetworkDao
import com.example.takao.androidboilerplate.dao.NetworkDaoMockImpl
import com.example.takao.androidboilerplate.repository.PingPongRepository
import com.example.takao.androidboilerplate.repository.PingPongRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AppModule {

    private const val BASE_URL = "https://api.github.com"

    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    @JvmStatic
    fun provideNetworkDao(): NetworkDao = NetworkDaoMockImpl()


    @Singleton
    @Provides
    @JvmStatic
    fun providesPingPongRepository(pingPongRepository: PingPongRepositoryImpl): PingPongRepository =
        pingPongRepository

}
