package com.moneyme.exam.main.entities

import com.moneyme.exam.core.extensions.allTrue

data class CountryItem(
    val id: Int,
    val flag: String? = null,
    val name: String? = null,
    val region: String? = null
) {

    override fun equals(other: Any?): Boolean {
        val otherObject = other as? CountryItem ?: return false
        return allTrue(
            id == otherObject.id,
            flag == otherObject.flag,
            name == other.name,
            region == other.region
        )
    }

    override fun hashCode() = System.identityHashCode(this)
}