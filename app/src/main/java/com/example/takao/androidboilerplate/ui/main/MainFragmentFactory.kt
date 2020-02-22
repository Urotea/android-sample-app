package com.example.takao.androidboilerplate.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.takao.androidboilerplate.ui.main.main.MainFragment
import com.example.takao.androidboilerplate.ui.main.next.NextFragment
import com.example.takao.androidboilerplate.ui.second.third.ThirdFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val mainFragment: MainFragment,
    private val nextFragment: NextFragment
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            // コンストラクタに引数を渡して生成
            MainFragment::class.java.name -> mainFragment
            NextFragment::class.java.name -> nextFragment
            else -> super.instantiate(classLoader, className)
        }
    }
}