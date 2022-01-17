package com.moneyme.exam.core.networking.entities

import com.google.android.gms.maps.model.LatLng
import com.moneyme.exam.core.room.entities.Country

data class CountryApi(
    val name: String? = null,
    val officialName: String? = null,
    val isIndependent: Boolean? = null,
    val isUNMember: Boolean? = null,
    val currencies: List<Currency>? = null,
    val capitals: String? = null,
    val region: String? = null,
    val subregion: String? = null,
    val languages: List<String>? = null,
    val latLng: LatLng? = null,
    val googleMapUrl: String? = null,
    val coatOfArm: String? = null,
    val flag: String? = null
) {
    data class Currency(
        val name: String? = null,
        val code: String? = null,
        val symbol: String? = null
    )

    fun toLocalCountry(country: Country): Country {
        return country.copy(
            name = name,
            officialName = officialName,
            isIndependent = isIndependent,
            isUNMember = isUNMember,
            capitals = capitals,
            region = region,
            subregion = subregion,
            latitude = latLng?.latitude,
            longitude = latLng?.longitude,
            googleMapUrl = googleMapUrl,
            coatOfArm = coatOfArm,
            flag = flag
        )
    }
}