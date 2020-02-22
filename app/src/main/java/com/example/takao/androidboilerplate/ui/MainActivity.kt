package com.example.takao.androidboilerplate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.takao.androidboilerplate.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity: AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = this.fragmentFactory
        setContentView(R.layout.main_activity)
    }


    override fun androidInjector(): AndroidInjector<Any> = this.injector
}
