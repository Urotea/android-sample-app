package com.example.takao.androidboilerplate.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.takao.androidboilerplate.actions.AppActions
import com.example.takao.androidboilerplate.reducer.AppReducer
import com.example.takao.androidboilerplate.state.AppState
import com.freeletics.coredux.createStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import javax.inject.Singleton

interface Dispatcher<T> {
    fun dispatch(action: T)
}

interface StateAccessor<T> {
    val state: LiveData<T>
}

@Singleton
class AppStore @Inject constructor(
    private val reducer: AppReducer
): Dispatcher<AppActions>, StateAccessor<AppState> {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val store = this.scope.createStore(
            name = "global",
            initialState = AppState(),
            reducer = this.reducer.reducer
        )
    private val _state = MutableLiveData<AppState>()
    override val state: LiveData<AppState> = this._state


    init {
        this.store.subscribe { state ->
            this._state.postValue(state)
        }
    }

    override fun dispatch(action: AppActions) {
        this.store.dispatch(action)
    }
}
