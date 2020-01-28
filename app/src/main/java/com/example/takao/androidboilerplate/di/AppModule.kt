package com.example.takao.androidboilerplate.di

import android.app.Application
import android.content.Context
import com.example.takao.androidboilerplate.actions.AppActions
import com.example.takao.androidboilerplate.dao.GithubApi
import com.example.takao.androidboilerplate.dao.NetworkDao
import com.example.takao.androidboilerplate.dao.NetworkDaoMockImpl
import com.example.takao.androidboilerplate.reducer.AppReducer
import com.example.takao.androidboilerplate.reducer.AppReducerImpl
import com.example.takao.androidboilerplate.repository.PingPongRepository
import com.example.takao.androidboilerplate.repository.PingPongRepositoryImpl
import com.example.takao.androidboilerplate.state.AppState
import com.example.takao.androidboilerplate.store.AppStore
import com.example.takao.androidboilerplate.store.Dispatcher
import com.example.takao.androidboilerplate.store.StateAccessor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
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
    fun provideGithubApi(): GithubApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        )
        .addConverterFactory(Json.nonstrict.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(GithubApi::class.java)

    @Singleton
    @Provides
    @JvmStatic
    fun provideNetworkDao(): NetworkDao = NetworkDaoMockImpl()


    @Singleton
    @Provides
    @JvmStatic
    fun providesPingPongRepository(pingPongRepository: PingPongRepositoryImpl): PingPongRepository =
        pingPongRepository

    @Singleton
    @Provides
    @JvmStatic
    fun providesMainActivityReducer(reducer: AppReducerImpl): AppReducer = reducer

    @Singleton
    @Provides
    @JvmStatic
    fun providesDispatcher(store: AppStore): Dispatcher<AppActions> = store

    @Singleton
    @Provides
    @JvmStatic
    fun providesStateAccessor(store: AppStore): StateAccessor<AppState> = store
}
