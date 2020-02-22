package com.example.takao.androidboilerplate.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import dagger.android.support.AndroidSupportInjection

abstract class MainActivityFragmentBase: Fragment() {

    override fun onAttach(context: Context) {
        // AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.activity?.onBackPressedDispatcher?.addCallback(this.viewLifecycleOwner, true) {
            this@MainActivityFragmentBase.onBackPressed()
        }
    }

    open fun onBackPressed() {
        this.view?.findNavController()?.popBackStack()
    }
}
