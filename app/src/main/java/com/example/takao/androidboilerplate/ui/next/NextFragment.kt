package com.example.takao.androidboilerplate.ui.next


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.takao.androidboilerplate.databinding.FragmentNextBinding
import com.example.takao.androidboilerplate.di.ViewModelFactory
import com.example.takao.androidboilerplate.store.MainActivityStore
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [NextFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class NextFragment : DaggerFragment() {
    @Inject
    lateinit var viewFactory: ViewModelFactory

    private val viewModel: MainActivityStore by lazy {
        ViewModelProviders.of(requireActivity(), this.viewFactory).get(MainActivityStore::class.java)
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
        }.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment NextFragment.
         */
        @JvmStatic
        fun newInstance() = NextFragment()
    }
}
