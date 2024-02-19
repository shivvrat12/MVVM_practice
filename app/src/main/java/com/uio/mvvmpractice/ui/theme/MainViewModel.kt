package com.uio.mvvmpractice.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    val list = mutableListOf<String>()

    fun increment(item:String){
           list.add(item)
    }
}