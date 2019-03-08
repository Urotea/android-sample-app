package com.example.takao.androidreduxsample.ui.next


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.takao.androidreduxsample.databinding.FragmentNextBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NextFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class NextFragment : Fragment() {
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
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = NextFragment()
    }
}
