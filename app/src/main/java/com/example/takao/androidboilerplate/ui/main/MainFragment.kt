package com.example.takao.androidboilerplate.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.takao.androidboilerplate.databinding.FragmentMainBinding
import com.example.takao.androidboilerplate.ui.MainActivityFragmentBase
import javax.inject.Inject

class MainFragment : MainActivityFragmentBase() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var actionCreator: MainFragmentActionCreator

    private val viewModel: MainFragmentViewModel by viewModels {
        this.factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMainBinding.inflate(inflater).apply {
            fragment = this@MainFragment
            viewModel = this@MainFragment.viewModel
            lifecycleOwner = this@MainFragment
        }.root
    }

    fun nextButtonClicked(view: View) =
        view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToNextFragment())

    fun incrementButtonClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        this.actionCreator.countUp()
    }
}
