package com.artivaticinterviewtest.APICall


import com.artivaticinterviewtest.model.CountryData
import com.artivaticinterviewtest.model.RowsItem

interface ApiHelper {
    suspend fun getApiData(): CountryData
}