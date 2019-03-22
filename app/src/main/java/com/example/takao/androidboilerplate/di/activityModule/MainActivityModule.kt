package com.example.takao.androidboilerplate.di.activityModule

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.di.ViewModelKey
import com.example.takao.androidboilerplate.reducer.MainActivityReducer
import com.example.takao.androidboilerplate.redux.Reducer
import com.example.takao.androidboilerplate.store.MainActivityState
import com.example.takao.androidboilerplate.store.MainActivityStore
import com.example.takao.androidboilerplate.ui.MainActivity
import com.example.takao.androidboilerplate.ui.main.MainFragment
import com.example.takao.androidboilerplate.ui.next.NextFragment
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
    fun providesMainActivityReducer(reducer: MainActivityReducer): Reducer<MainActivityActions, MainActivityState>


    @Binds
    @IntoMap
    @ViewModelKey(MainActivityStore::class)
    fun bindMainViewStore(
        mainActivityStore: MainActivityStore
    ): ViewModel
}
