package com.mayuresh.countries.domain.usecase

import android.content.Context
import com.mayuresh.countries.R
import com.mayuresh.countries.data.mapper.CountryDetailsMapper
import com.mayuresh.countries.data.model.CountryModel
import com.mayuresh.countries.data.util.Response
import com.mayuresh.countries.domain.model.CountryDetailsUiState
import com.mayuresh.countries.domain.repository.CountryDetailsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountryInformationUseCase @Inject constructor(
    private val countryDetailsRepository: CountryDetailsRepository,
    @ApplicationContext val context: Context
){
    suspend fun invoke(code: String): Flow<Response<CountryDetailsUiState>> =
        flow {
            val response = countryDetailsRepository.getCountryDetails(code)
            if (response.isSuccessful) {
                val countryList = response.body() as List<CountryModel>
                if (countryList.isNotEmpty()) {
                    val result = CountryDetailsMapper().mapFrom(countryList.get(0))
                    emit(Response.Success(result))
                } else {
                    emit(
                        Response.Error(
                            code = 500,
                            message = context.getString(R.string.something_went_wrong)
                        )
                    )
                }
            } else {
                emit(Response.Error(code = response.code(), message = response.message()))
            }
        }
}