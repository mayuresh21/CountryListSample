package com.mayuresh.countries.domain.model

data class CountryDetailsUiState(
    val name: String = "",
    val flagImageUrl: String = "",
    val population: String = "",
    val region: String = "",
    val subregion: String = "",
    var currencies: String = "",
    var languages: String = "",
    val googleMapUrl: String = "",
)
