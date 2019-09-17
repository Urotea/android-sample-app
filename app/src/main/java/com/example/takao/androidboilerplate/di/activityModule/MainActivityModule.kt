package com.example.takao.androidboilerplate.di.activityModule

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.takao.androidboilerplate.actions.AppActions
import com.example.takao.androidboilerplate.di.ViewModelFactory
import com.example.takao.androidboilerplate.di.ViewModelKey
import com.example.takao.androidboilerplate.state.AppState
import com.example.takao.androidboilerplate.store.Dispatcher
import com.example.takao.androidboilerplate.store.AppStore
import com.example.takao.androidboilerplate.store.StateAccessor
import com.example.takao.androidboilerplate.ui.MainActivity
import com.example.takao.androidboilerplate.ui.main.MainFragment
import com.example.takao.androidboilerplate.ui.main.MainFragmentViewModel
import com.example.takao.androidboilerplate.ui.next.NextFragment
import com.example.takao.androidboilerplate.ui.next.NextFragmentViewModel
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

    @Binds
    fun providesDispatcher(store: AppStore): Dispatcher<AppActions>

    @Binds
    fun providesStateAccessor(store: AppStore): StateAccessor<AppState>

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NextFragmentViewModel::class)
    fun bindNextFragmentViewModel(nextFragmentViewModel: NextFragmentViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
