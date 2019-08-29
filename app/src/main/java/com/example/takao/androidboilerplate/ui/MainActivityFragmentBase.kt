package com.example.takao.androidboilerplate.ui

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class MainActivityFragmentBase: Fragment() {

    protected val viewModel: MainActivityViewModel by lazy {
        val hasViewModel = this.requireActivity() as HasViewModel
        hasViewModel.viewModel
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}