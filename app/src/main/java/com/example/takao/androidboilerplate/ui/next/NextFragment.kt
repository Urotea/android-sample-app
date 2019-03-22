package com.example.takao.androidboilerplate.ui.next


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.databinding.FragmentNextBinding
import com.example.takao.androidboilerplate.di.ViewModelFactory
import com.example.takao.androidboilerplate.store.MainActivityStore
import dagger.android.support.DaggerFragment
import timber.log.Timber
import java.time.OffsetDateTime
import javax.inject.Inject

class NextFragment : DaggerFragment() {
    @Inject
    lateinit var viewFactory: ViewModelFactory

    private val viewModel: NextFragmentViewModel by lazy {
        ViewModelProviders.of(requireActivity(), this.viewFactory).get(MainActivityStore::class.java)
    }

    private val props: NextFragmentProps by lazy {
        this.viewModel.nextFragmentProps
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // debug
        this.props.label.observe(this, Observer {
            Timber.d("test")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentNextBinding.inflate(inflater).apply {
            fragment = this@NextFragment
            lifecycleOwner = this@NextFragment
            props = this@NextFragment.props
        }.root
    }

    fun onPingButtonClicked(@Suppress("UNUSED_PARAMETER") view: View) {
        this.viewModel.dispatch(MainActivityActions.PingButtonClicked(OffsetDateTime.now()))
    }
}
