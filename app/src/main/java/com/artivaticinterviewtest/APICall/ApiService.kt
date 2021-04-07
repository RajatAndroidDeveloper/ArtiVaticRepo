package com.artivaticinterviewtest.APICall

import com.artivaticinterviewtest.model.ApiUser
import com.artivaticinterviewtest.model.CountryData
import com.artivaticinterviewtest.model.RowsItem
import retrofit2.http.GET

interface ApiService {
    @GET("v3/c4ab4c1c-9a55-4174-9ed2-cbbe0738eedf")
    suspend fun getApiData(): CountryData
}