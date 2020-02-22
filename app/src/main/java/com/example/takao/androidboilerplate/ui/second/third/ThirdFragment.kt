package com.example.takao.androidboilerplate.ui.second.third


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.takao.androidboilerplate.databinding.FragmentThirdBinding
import com.example.takao.androidboilerplate.ui.FragmentBase
import com.example.takao.androidboilerplate.ui.second.third.epoxy.GithubController
import javax.inject.Inject

class ThirdFragment @Inject constructor(
    private val factory: ViewModelProvider.Factory,
    private val actionCreator: ThirdFragmentActionCreator
) : FragmentBase(), GithubController.CardClickListener {

    companion object {
        val OWNER_LIST: List<String> = listOf("Urotea", "googlesamples", "kotlin", "android", "jetbrains")
    }

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
        this.actionCreator.setOwnerList(OWNER_LIST, this.lifecycleScope)
    }

    override fun onClickCard(userName: String) {
        this.actionCreator.ownerSelected(userName, this.lifecycleScope)
    }

}
