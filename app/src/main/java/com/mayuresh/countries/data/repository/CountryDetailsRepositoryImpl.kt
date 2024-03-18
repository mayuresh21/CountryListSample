package com.mayuresh.countries.data.repository

import com.mayuresh.countries.data.model.CountryModel
import com.mayuresh.countries.data.service.ApiService
import com.mayuresh.countries.domain.repository.CountryDetailsRepository
import retrofit2.Response
import javax.inject.Inject

class CountryDetailsRepositoryImpl @Inject constructor(private val apiService: ApiService) : CountryDetailsRepository{
    override suspend fun getCountryDetails(code: String): Response<List<CountryModel>> {
        return apiService.getSingleCountryDetails(code)
    }

}