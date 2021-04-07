package com.artivaticinterviewtest.APICall

import com.artivaticinterviewtest.APICall.ApiHelper
import com.artivaticinterviewtest.APICall.ApiService

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getApiData() = apiService.getApiData()
}