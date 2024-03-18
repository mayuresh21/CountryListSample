package com.mayuresh.countries.data.model.countryinfo


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class DemonymEnglish(
    @SerializedName("f")
    @Expose
    val female: String,
    @SerializedName("m")
    @Expose
    val male: String
)