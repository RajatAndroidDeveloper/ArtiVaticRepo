package com.artivaticinterviewtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artivaticinterviewtest.APICall.ApiHelper
import com.artivaticinterviewtest.model.ApiUser
import com.artivaticinterviewtest.model.CountryData
import com.artivaticinterviewtest.model.RowsItem
import com.artivaticinterviewtest.utils.Resource
import kotlinx.coroutines.launch

class SingleNetworkCallViewModel(
    private val apiHelper: ApiHelper,
) : ViewModel() {

    private val rowsItems = MutableLiveData<Resource<CountryData>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            rowsItems.postValue(Resource.loading(null))
            try {
                val usersFromApi = apiHelper.getApiData()
                rowsItems.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                rowsItems.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<CountryData>> {
        return rowsItems
    }

}