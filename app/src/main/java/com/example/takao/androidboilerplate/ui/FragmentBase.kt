package com.example.takao.androidboilerplate.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

abstract class FragmentBase: Fragment() {

    override fun onAttach(context: Context) {
        // AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.activity?.onBackPressedDispatcher?.addCallback(this.viewLifecycleOwner, true) {
            this@FragmentBase.onBackPressed()
        }
    }

    open fun onBackPressed() {
        val result = this.view?.findNavController()?.popBackStack() ?: return
        if(!result) {
            this.requireActivity().finish()
        }
    }
}
