package com.example.takao.androidboilerplate.ui.third


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.takao.androidboilerplate.R
import com.example.takao.androidboilerplate.databinding.FragmentThirdBinding
import com.example.takao.androidboilerplate.ui.MainActivityFragmentBase
import com.example.takao.androidboilerplate.ui.third.epoxy.GithubController
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ThirdFragment : MainActivityFragmentBase(), GithubController.CardClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var actionCreator: ThirdFragmentActionCreator

    private val viewModel: ThirdFragmentViewModel by viewModels {
        this.factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentThirdBinding.inflate(inflater)
        val controller = GithubController(this)
        val linearLayout = LinearLayoutManager(this.requireContext())
        binding.githubRecyclerView.also {
            it.adapter = controller.adapter
            it.layoutManager = linearLayout
            it.addItemDecoration(DividerItemDecoration(this.requireContext(), linearLayout.orientation))
        }
        controller.requestModelBuild()
        viewModel.also {
            it.owner.observe(this.viewLifecycleOwner, Observer { githubOwnerList ->
                controller.owners = githubOwnerList
                controller.requestModelBuild()
            })
            it.repos.observe(this.viewLifecycleOwner, Observer { githubRepoList ->
                controller.submitList(githubRepoList)
            })
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        this.viewModel.getOwner()
    }

    override fun onClickCard(userName: String) {
    }

}
