package com.mayuresh.countries.domain.repository

import com.mayuresh.countries.data.model.CountryModel
import retrofit2.Response

interface CountryDetailsRepository {

    suspend fun getCountryDetails(code: String): Response<List<CountryModel>>

}