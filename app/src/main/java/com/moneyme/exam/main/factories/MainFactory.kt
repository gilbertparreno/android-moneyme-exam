package com.moneyme.exam.main.factories

import com.moneyme.exam.core.room.entities.Country
import com.moneyme.exam.main.entities.CountryItem

object MainFactory {

    fun getCountryItems(countries: List<Country>): List<CountryItem> {
        return countries.map {
            CountryItem(
                id = it.id,
                name = it.name,
                region = if (it.subregion != null) {
                    it.region.plus(", ").plus(it.subregion)
                } else {
                    it.region
                },
                flag = it.flag
            )
        }
    }
}