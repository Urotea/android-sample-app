package com.example.takao.androidboilerplate.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.takao.androidboilerplate.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity: AppCompatActivity(), HasAndroidInjector, HasViewModel {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = this.injector


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override val viewModel: MainActivityViewModel by viewModels {
        factory
    }
}

interface HasViewModel {
    val viewModel: MainActivityViewModel
}
