package com.example.takao.androidboilerplate.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.databinding.FragmentMainBinding
import com.example.takao.androidboilerplate.di.ViewModelFactory
import com.example.takao.androidboilerplate.store.MainActivityStore
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {
    @Inject
    lateinit var viewFactory: ViewModelFactory

    private val viewModel: MainActivityStore by lazy {
        ViewModelProviders.of(requireActivity(), this.viewFactory).get(MainActivityStore::class.java)
    }

    private val props: MainFragmentProps by lazy {
        this.viewModel.toMainFragmentProps()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMainBinding.inflate(inflater).apply {
            fragment = this@MainFragment
            props = this@MainFragment.props
            lifecycleOwner = this@MainFragment
        }.root
    }

    fun nextButtonClicked(view: View) =
        view.findNavController().navigate(MainFragmentDirections.actionMainFragmentToNextFragment())

    fun incrementButtonClicked(view: View) {
        this.viewModel.dispatch(MainActivityActions.IncrementButtonClicked)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MainFragment.
         */
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
