package com.mayuresh.countries.data.model.countryinfo


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Maps(
    @SerializedName("googleMaps")
    @Expose
    val googleMaps: String,
    @SerializedName("openStreetMaps")
    @Expose
    val openStreetMaps: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Maps

        if ((googleMaps ?: "") != other.googleMaps) return false

        return true
    }

    override fun hashCode(): Int {
        val result = googleMaps.hashCode() ?: 0
        return result
    }
}