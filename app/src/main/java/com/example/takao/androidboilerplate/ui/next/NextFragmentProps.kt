package com.example.takao.androidboilerplate.ui.next

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

data class NextFragmentProps(
    val label: LiveData<String>
): ViewModel()
