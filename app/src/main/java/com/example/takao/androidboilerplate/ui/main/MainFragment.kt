package com.example.takao.androidboilerplate.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.takao.androidboilerplate.databinding.FragmentMainBinding
import com.example.takao.androidboilerplate.ui.MainActivityFragmentBase
import javax.inject.Inject

class MainFragment @Inject constructor(
    private val factory: ViewModelProvider.Factory,
    private val actionCreator: MainFragmentActionCreator

) : MainActivityFragmentBase() {

    private val viewModel: MainFragmentViewModel by viewModels {
        this.factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        this.activity?.finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.viewModel.shouldGoToNext.observe(this.viewLifecycleOwner, Observer {
            if(it) {
                this.actionCreator.leavePage()
                this.view?.findNavController()?.navigate(MainFragmentDirections.actionMainFragmentToNextFragment())
            }
        })

        // Inflate the layout for this fragment
        return FragmentMainBinding.inflate(inflater).apply {
            fragment = this@MainFragment
            viewModel = this@MainFragment.viewModel
            lifecycleOwner = this@MainFragment.viewLifecycleOwner
        }.root
    }

    fun nextButtonClicked(@Suppress("UNUSED_PARAMETER") view: View) =
        this.actionCreator.goNextPage()

    fun incrementButtonClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        this.actionCreator.countUp()
    }
}
