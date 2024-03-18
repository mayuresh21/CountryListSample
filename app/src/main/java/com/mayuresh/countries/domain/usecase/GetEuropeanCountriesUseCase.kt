package com.mayuresh.countries.domain.usecase

import com.mayuresh.countries.data.mapper.EuropeCountryListMapper
import com.mayuresh.countries.data.model.CountryModel
import com.mayuresh.countries.domain.model.CountryListUiState
import com.mayuresh.countries.domain.repository.EuropeanCountriesListRepository
import com.mayuresh.countries.data.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This use case is used for fetch data from repository and pass to view model
 * @param europeCountriesListRepository
 */
class GetEuropeanCountriesUseCase @Inject constructor(private val europeCountriesListRepository: EuropeanCountriesListRepository) {

    suspend fun invoke(): Flow<Response<List<CountryListUiState>>> =
        flow {
            val response = europeCountriesListRepository.getEuropeCountriesList()
            if (response.isSuccessful) {
                val countriesList = response.body() as List<CountryModel>
                val result = EuropeCountryListMapper().mapFrom(countriesList)
                emit(Response.Success(result))
            } else {
                emit(Response.Error(code = response.code(), message = response.message()))
            }
        }

}