package com.example.takao.androidreduxsample.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.takao.androidreduxsample.actions.Actions

class MainViewStore : ViewModel() {

    private val _state = MutableLiveData(State())
    val state: LiveData<State> = this._state

    fun dispatch(action: Actions) {

    }
}
