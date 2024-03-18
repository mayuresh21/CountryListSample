package com.mayuresh.countries.data.model.countryinfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("symbol")
    @Expose
    val symbol: String,
)