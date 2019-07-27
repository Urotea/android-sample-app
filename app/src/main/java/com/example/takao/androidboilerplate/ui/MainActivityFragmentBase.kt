package com.example.takao.androidboilerplate.ui

import androidx.lifecycle.ViewModelProviders
import com.example.takao.androidboilerplate.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class MainActivityFragmentBase: DaggerFragment() {
    @Inject
    lateinit var viewFactory: ViewModelFactory

    protected val viewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(requireActivity(), this.viewFactory).get(MainActivityViewModel::class.java)
    }
}