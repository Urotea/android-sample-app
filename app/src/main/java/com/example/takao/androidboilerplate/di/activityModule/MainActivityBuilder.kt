package com.example.takao.androidboilerplate.di.activityModule

import com.example.takao.androidboilerplate.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityBuilder {
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    fun contributeMainActivity(): MainActivity
}
