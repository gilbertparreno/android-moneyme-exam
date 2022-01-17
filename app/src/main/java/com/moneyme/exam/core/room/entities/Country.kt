package com.moneyme.exam.core.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "countries", indices = [Index(value = ["name"], unique = true)])
data class Country(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val name: String? = null,
    @ColumnInfo(name = "official_name") val officialName: String? = null,
    @ColumnInfo(name = "is_independent") val isIndependent: Boolean? = null,
    @ColumnInfo(name = "is_un_member") val isUNMember: Boolean? = null,
    @ColumnInfo val capitals: String? = null,
    @ColumnInfo val region: String? = null,
    @ColumnInfo val subregion: String? = null,
    @ColumnInfo val latitude: Double? = null,
    @ColumnInfo val longitude: Double? = null,
    @ColumnInfo(name = "google_map_erl") val googleMapUrl: String? = null,
    @ColumnInfo(name = "coat_of_arm") val coatOfArm: String? = null,
    @ColumnInfo(name = "flag") val flag: String? = null,
) : RoomEntity