package com.mayuresh.countries.data.model.countryinfo


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Flags(
    @SerializedName("png")
    @Expose
    val png: String,
    @SerializedName("alt")
    @Expose
    val alt: String
)