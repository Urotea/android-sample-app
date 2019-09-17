package com.example.takao.androidboilerplate.ui.next


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.takao.androidboilerplate.databinding.FragmentNextBinding
import com.example.takao.androidboilerplate.ui.MainActivityFragmentBase
import javax.inject.Inject

class NextFragment : MainActivityFragmentBase() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var actionCreator: NextFragmentActionCreator

    private val viewModel: NextFragmentViewModel by viewModels {
        this.factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentNextBinding.inflate(inflater).apply {
            fragment = this@NextFragment
            lifecycleOwner = this@NextFragment
            viewModel = this@NextFragment.viewModel
        }.root
    }

    fun onPingButtonClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        this.actionCreator.pingPong(this.lifecycleScope)
    }
}
