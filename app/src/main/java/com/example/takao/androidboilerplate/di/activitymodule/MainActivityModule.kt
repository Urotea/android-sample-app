package com.example.takao.androidboilerplate.di.activitymodule

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.di.FragmentKey
import com.example.takao.androidboilerplate.di.ViewModelKey
import com.example.takao.androidboilerplate.ui.main.MainActivity
import com.example.takao.androidboilerplate.ui.main.main.MainFragment
import com.example.takao.androidboilerplate.ui.main.main.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {
    @Binds
    fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    fun bindMainFragment(fragment: MainFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel

}
