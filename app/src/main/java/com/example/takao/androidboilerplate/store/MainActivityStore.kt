package com.example.takao.androidboilerplate.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.reducer.MainActivityReducer
import com.example.takao.androidboilerplate.state.MainActivityState
import com.freeletics.coredux.createStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject

interface MainActivityStore {
    val state: LiveData<MainActivityState>
    fun dispatch(action: MainActivityActions)
}

class MainActivityStoreImpl @Inject constructor(
    private val reducer: MainActivityReducer
): MainActivityStore {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    private val store = scope.createStore(
            name = "global",
            initialState = MainActivityState(),
            reducer = this.reducer.reducer
        )
    private val _state = MutableLiveData<MainActivityState>()
    override val state: LiveData<MainActivityState> = this._state


    init {
        this.store.subscribe { state ->
            this._state.postValue(state)
        }
    }

    override fun dispatch(action: MainActivityActions) {
        this.store.dispatch(action)
    }
}
