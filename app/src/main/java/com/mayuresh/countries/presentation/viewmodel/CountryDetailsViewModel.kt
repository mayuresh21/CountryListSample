package com.mayuresh.countries.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayuresh.countries.data.util.Response
import com.mayuresh.countries.domain.model.CountryDetailsUiState
import com.mayuresh.countries.domain.usecase.GetCountryInformationUseCase
import com.mayuresh.countries.presentation.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model is responsible for pass article list to composable functions
 * @param getCountryInformationUseCase
 * @param networkHelper
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val getCountryInformationUseCase: GetCountryInformationUseCase,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    var isInternetAvailable = MutableStateFlow(true)

    var isError = MutableStateFlow(false)

    var isLoading = MutableStateFlow(true)

    private val _countryDetailsUiState = MutableStateFlow<CountryDetailsUiState>(
        CountryDetailsUiState()
    )
    val countryDetailsUiState: StateFlow<CountryDetailsUiState> = _countryDetailsUiState


    fun fetchCountries(countryCode: String) {
        viewModelScope.launch {
            try {
                if (networkHelper.isNetworkConnected()) {
                    getCountryInformationUseCase.invoke(countryCode)
                        .collect() { response ->
                            when (response) {
                                is Response.Success -> {
                                    _countryDetailsUiState.value = response.data
                                    isError.value = false
                                    isLoading.value = false
                                }

                                is Response.Error -> {
                                    isError.value = true
                                    isLoading.value = false
                                }

                                else -> {
                                    isError.value = true
                                    isLoading.value = false
                                }
                            }
                        }
                    isInternetAvailable.value = true
                } else {
                    isInternetAvailable.value = false
                    isLoading.value = false
                }
            } catch (e: Exception) {
                isError.value = true
                isLoading.value = false
            }
        }
    }
}