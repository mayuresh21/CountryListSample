package com.mayuresh.countries.domain.repository

import com.mayuresh.countries.data.model.CountryModel
import retrofit2.Response

interface EuropeanCountriesListRepository {

    suspend fun getEuropeCountriesList(): Response<List<CountryModel>>

}