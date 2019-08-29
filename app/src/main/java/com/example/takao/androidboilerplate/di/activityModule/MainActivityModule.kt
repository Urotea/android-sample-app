package com.example.takao.androidboilerplate.di.activityModule

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.takao.androidboilerplate.di.ViewModelFactory
import com.example.takao.androidboilerplate.di.ViewModelKey
import com.example.takao.androidboilerplate.reducer.MainActivityReducer
import com.example.takao.androidboilerplate.reducer.MainActivityReducerImpl
import com.example.takao.androidboilerplate.sideEffect.MainActivitySideEffects
import com.example.takao.androidboilerplate.sideEffect.MainActivitySideEffectsImpl
import com.example.takao.androidboilerplate.store.MainActivityStore
import com.example.takao.androidboilerplate.store.MainActivityStoreImpl
import com.example.takao.androidboilerplate.ui.MainActivity
import com.example.takao.androidboilerplate.ui.MainActivityViewModel
import com.example.takao.androidboilerplate.ui.main.MainFragment
import com.example.takao.androidboilerplate.ui.next.NextFragment
import com.freeletics.rxredux.SideEffect
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
    fun providesMainActivityReducer(reducer: MainActivityReducerImpl): MainActivityReducer

    @Binds
    fun providesMainActivityStore(store: MainActivityStoreImpl): MainActivityStore

    @Binds
    fun providesMainActivitySideEffect(sideEffects: MainActivitySideEffectsImpl): MainActivitySideEffects

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainViewViewModel(
        mainActivityViewModel: MainActivityViewModel
    ): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
