package com.artivaticinterviewtest.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artivaticinterviewtest.APICall.ApiHelper
import com.artivaticinterviewtest.ui.SingleNetworkCallViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}