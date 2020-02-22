package com.example.takao.androidboilerplate.ui.next


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.takao.androidboilerplate.databinding.FragmentNextBinding
import com.example.takao.androidboilerplate.ui.MainActivityFragmentBase
import javax.inject.Inject

class NextFragment @Inject constructor(
    private val factory: ViewModelProvider.Factory,
    private val actionCreator: NextFragmentActionCreator
) : MainActivityFragmentBase() {

    private val viewModel: NextFragmentViewModel by viewModels {
        this.factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        this.actionCreator.leavePage()
        this.view?.findNavController()?.popBackStack()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentNextBinding.inflate(inflater).apply {
            fragment = this@NextFragment
            lifecycleOwner = this@NextFragment.viewLifecycleOwner
            viewModel = this@NextFragment.viewModel
        }.root
    }

    fun onPingButtonClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        this.actionCreator.pingPong(this.lifecycleScope)
    }

    fun onNextButtonClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        this.view?.findNavController()?.navigate(NextFragmentDirections.actionNextFragmentToThirdFragment())
    }
}
