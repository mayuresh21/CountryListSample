package com.mayuresh.countries.data.mapper

import com.mayuresh.countries.data.model.CountryModel
import com.mayuresh.countries.domain.model.CountryListUiState
import javax.inject.Inject

/**
 * This class is used for map the server response to desired response.
 */
class EuropeCountryListMapper @Inject constructor() :
    Mapper<List<CountryModel>, List<CountryListUiState>> {

    override fun mapFrom(fromList: List<CountryModel>): List<CountryListUiState> {
        val countryList = mutableListOf<CountryListUiState>()
        for (from in fromList){
            countryList.add(
                CountryListUiState(
                name = from.name.common,
                flagImageUrl = from.flags?.png ?: "",
                countryCode = from.ccn3 ?: "",
                region = from.region ?: "",
                subregion = from.subregion ?: ""
            )
            )
        }
        return countryList
    }
}