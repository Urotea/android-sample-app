package com.example.takao.androidboilerplate.di.activitymodule

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.takao.androidboilerplate.di.CustomFragmentFactory
import com.example.takao.androidboilerplate.di.ViewModelFactory
import com.example.takao.androidboilerplate.ui.main.MainActivity
import com.example.takao.androidboilerplate.ui.second.SecondActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(
        modules = [
            SecondActivityModule::class
        ]
    )
    fun contributeSecondActivity(): SecondActivity

    @Binds
    fun bindsFragmentFactory(factory: CustomFragmentFactory): FragmentFactory

    @Binds
    fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
