package com.uio.mvvmpractice.ui.theme


import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    var list = mutableListOf<String>()

    fun add(item:String){
           list.add(item)
    }

    fun delete(item:String){
        list.remove(item)
    }
}