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
import com.example.takao.androidboilerplate.ui.second.SecondActivity
import com.example.takao.androidboilerplate.ui.second.third.ThirdFragment
import com.example.takao.androidboilerplate.ui.second.third.ThirdFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SecondActivityModule {
    @Binds
    fun bindSecondActivity(secondActivity: SecondActivity): AppCompatActivity

    @Binds
    @IntoMap
    @FragmentKey(ThirdFragment::class)
    fun bindThirdFragment(fragment: ThirdFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(ThirdFragmentViewModel::class)
    fun bindThirdFragmentViewModel(thirdFragmentViewModel: ThirdFragmentViewModel): ViewModel

}