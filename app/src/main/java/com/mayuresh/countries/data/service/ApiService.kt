package com.mayuresh.countries.data.service

import com.mayuresh.countries.data.model.CountryModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/v3.1/region/europe")
    suspend fun getEuropeCountriesData(): Response<List<CountryModel>>

    @GET("/v3.1/alpha/{code}")
    suspend fun getSingleCountryDetails(
        @Path("code") code: String,
    ): Response<List<CountryModel>>

}