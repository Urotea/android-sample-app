package com.example.takao.androidboilerplate.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.takao.androidboilerplate.ui.main.MainFragment
import com.example.takao.androidboilerplate.ui.next.NextFragment
import com.example.takao.androidboilerplate.ui.third.ThirdFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val mainFragment: MainFragment,
    private val nextFragment: NextFragment,
    private val thirdFragment: ThirdFragment
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            // コンストラクタに引数を渡して生成
            MainFragment::class.java.name -> mainFragment
            NextFragment::class.java.name -> nextFragment
            ThirdFragment::class.java.name -> thirdFragment
            else -> super.instantiate(classLoader, className)
        }
    }
}