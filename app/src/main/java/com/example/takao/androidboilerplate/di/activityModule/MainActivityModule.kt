package com.example.takao.androidboilerplate.di.activityModule

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.takao.androidboilerplate.di.ViewModelFactory
import com.example.takao.androidboilerplate.di.ViewModelKey
import com.example.takao.androidboilerplate.ui.MainActivity
import com.example.takao.androidboilerplate.ui.main.MainFragment
import com.example.takao.androidboilerplate.ui.main.MainFragmentViewModel
import com.example.takao.androidboilerplate.ui.next.NextFragment
import com.example.takao.androidboilerplate.ui.next.NextFragmentViewModel
import com.example.takao.androidboilerplate.ui.third.ThirdFragment
import com.example.takao.androidboilerplate.ui.third.ThirdFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {
    @Binds
    fun providesAppCompatActivity(mainActivity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    fun contributeNextFragment(): NextFragment

    @ContributesAndroidInjector
    fun contributeThridFragment(): ThirdFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NextFragmentViewModel::class)
    fun bindNextFragmentViewModel(nextFragmentViewModel: NextFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ThirdFragmentViewModel::class)
    fun bindThirdFragmentViewModel(thirdFragmentViewModel: ThirdFragmentViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
