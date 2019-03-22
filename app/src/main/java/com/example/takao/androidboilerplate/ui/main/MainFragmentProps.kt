package com.example.takao.androidboilerplate.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

data class MainFragmentProps(
    val numberLabel: LiveData<String>
): ViewModel()
