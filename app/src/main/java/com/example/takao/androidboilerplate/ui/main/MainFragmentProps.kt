package com.example.takao.androidboilerplate.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.store.MainActivityStore

data class MainFragmentProps(
    val numberLabel: LiveData<String>
): ViewModel()

fun MainActivityStore.toMainFragmentProps(): MainFragmentProps {
    return MainFragmentProps(
        numberLabel = Transformations.map(this.mainActivityState) {
            return@map "count: ${it.num}"
        }
    )
}
