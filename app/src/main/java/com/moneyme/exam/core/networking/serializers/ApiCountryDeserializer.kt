package com.moneyme.exam.core.networking.serializers

import com.google.android.gms.maps.model.LatLng
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.moneyme.exam.core.networking.entities.CountryApi
import java.lang.reflect.Type

class ApiCountryDeserializer : JsonDeserializer<CountryApi> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CountryApi {
        var apiCountry = CountryApi()
        json?.asJsonObject?.apply {
            get("name")?.asJsonObject?.apply {
                apiCountry = apiCountry.copy(name = get("common")?.asString)
                apiCountry = apiCountry.copy(officialName = get("official")?.asString)
            }

            val currencies = get("currencies")?.asJsonObject?.entrySet()
                ?.mapNotNull {
                    context?.deserialize<CountryApi.Currency>(
                        it.value.asJsonObject.asJsonObject,
                        CountryApi.Currency::class.java
                    )?.copy(code = it.key)
                }

            val languages = get("languages")?.asJsonObject?.entrySet()
                ?.mapNotNull { it.value.asString }

            val capital = get("capital")?.asJsonArray
                ?.mapNotNull { it.asString }
                ?.joinToString { it }

            val latLng = get("latlng")?.asJsonArray?.let { jsonArray ->
                val latitude = jsonArray.get(0)?.asDouble
                val longitude = jsonArray.get(1)?.asDouble
                LatLng(latitude!!, longitude!!)
            }

            apiCountry = apiCountry.copy(
                isIndependent = get("independent")?.asBoolean,
                isUNMember = get("unMember")?.asBoolean,
                currencies = currencies,
                capitals = capital,
                region = get("region")?.asString,
                subregion = get("subregion")?.asString,
                languages = languages,
                latLng = latLng,
                googleMapUrl = get("maps")?.asJsonObject?.get("googleMaps")?.asString,
                coatOfArm = get("coatOfArms")?.asJsonObject?.get("png")?.asString,
                flag = get("flags")?.asJsonObject?.get("png")?.asString
            )
        }
        return apiCountry
    }
}