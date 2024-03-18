package com.mayuresh.countries.domain.model

data class CountryListUiState(
    val name: String = "",
    val flagImageUrl: String = "",
    val countryCode: String = "",
    val region: String = "",
    val subregion: String = ""
)
