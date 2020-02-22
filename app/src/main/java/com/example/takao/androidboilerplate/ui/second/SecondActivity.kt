package com.example.takao.androidboilerplate.ui.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.example.takao.androidboilerplate.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SecondActivity: AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        supportFragmentManager.fragmentFactory = this.fragmentFactory
        setContentView(R.layout.activity_second)
    }

    override fun androidInjector(): AndroidInjector<Any> = this.injector
}
