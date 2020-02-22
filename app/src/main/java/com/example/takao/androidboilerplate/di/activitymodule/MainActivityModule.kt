package com.example.takao.androidboilerplate.di.activitymodule

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.takao.androidboilerplate.di.CustomFragmentFactory
import com.example.takao.androidboilerplate.di.FragmentKey
import com.example.takao.androidboilerplate.di.ViewModelFactory
import com.example.takao.androidboilerplate.di.ViewModelKey
import com.example.takao.androidboilerplate.ui.main.MainActivity
import com.example.takao.androidboilerplate.ui.main.main.MainFragment
import com.example.takao.androidboilerplate.ui.main.main.MainFragmentViewModel
import com.example.takao.androidboilerplate.ui.main.next.NextFragment
import com.example.takao.androidboilerplate.ui.main.next.NextFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

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
    @FragmentKey(NextFragment::class)
    fun bindNextFragment(fragment: NextFragment): Fragment


    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NextFragmentViewModel::class)
    fun bindNextFragmentViewModel(nextFragmentViewModel: NextFragmentViewModel): ViewModel

}
