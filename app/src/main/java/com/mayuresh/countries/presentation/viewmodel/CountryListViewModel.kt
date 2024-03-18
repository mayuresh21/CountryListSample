package com.mayuresh.countries.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayuresh.countries.data.util.Response
import com.mayuresh.countries.domain.model.CountryListUiState
import com.mayuresh.countries.domain.usecase.GetEuropeanCountriesUseCase
import com.mayuresh.countries.presentation.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model is responsible for pass article list to composable functions
 * @param getEuropeanCountriesUseCase
 * @param networkHelper
 */
@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getEuropeanCountriesUseCase: GetEuropeanCountriesUseCase,
    val networkHelper: NetworkHelper
) : ViewModel() {

    var isInternetAvailable = MutableStateFlow(true)

    var isError = MutableStateFlow(false)

    var isLoading = MutableStateFlow(true)

    private val _countriesUiState = MutableStateFlow<List<CountryListUiState>>(emptyList())
    val countriesUiState: StateFlow<List<CountryListUiState>> = _countriesUiState

    init {
        fetchEuropeCountries()
    }

    private fun fetchEuropeCountries() {
        viewModelScope.launch {
            try {
                if (networkHelper.isNetworkConnected()) {
                    getEuropeanCountriesUseCase.invoke()
                        .collect() { response ->
                            when (response) {
                                is Response.Success -> {
                                    _countriesUiState.value = response.data
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