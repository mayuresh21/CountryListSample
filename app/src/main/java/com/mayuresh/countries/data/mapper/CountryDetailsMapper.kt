package com.mayuresh.countries.data.mapper

import com.mayuresh.countries.data.model.CountryModel
import com.mayuresh.countries.domain.model.CountryDetailsUiState
import com.mayuresh.countries.data.model.countryinfo.Currency
import com.mayuresh.countries.domain.util.toReadable
import javax.inject.Inject

/**
 * This class is used for map the server response to desired response.
 */
class CountryDetailsMapper @Inject constructor() : Mapper<CountryModel, CountryDetailsUiState> {

    override fun mapFrom(from: CountryModel): CountryDetailsUiState {
        return CountryDetailsUiState(
            name = from.name.common,
            flagImageUrl = from.flags?.png ?: "",
            population = from.population.toReadable(),
            region = from.region ?: "",
            subregion = from.subregion ?: "",
            currencies = getCommaSeparatedCurrency(from.currencies),
            languages = getCommaSeparatedLanguage(from.languages),
            googleMapUrl = from.maps?.googleMaps ?: ""
        )
    }

    private fun getCommaSeparatedCurrency(map: Map<String, Currency>?): String {
        val str = StringBuilder("")
        if (!map.isNullOrEmpty()) {
            for ((key, currency) in map) {
                if (str.isNotEmpty()) str.append(", ") else str.append("${currency.name} (${currency.symbol})")
            }
        }
        return str.toString()
    }

    private fun getCommaSeparatedLanguage(map: Map<String, String>?): String {
        val str = StringBuilder("")
        if (!map.isNullOrEmpty()) {
            for ((key, name) in map) {
                if (str.isNotEmpty()) str.append(", ") else str.append(name)
            }
        }
        return str.toString()
    }
}