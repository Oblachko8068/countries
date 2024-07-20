package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Country

@Entity(
    tableName = "country"
)

data class CountryDbEntity(
    @PrimaryKey val country: String
) {
    fun toCountryDb(): Country = Country(
        country = country
    )
}

fun List<String>.fromCountrySetToCountryDbEntity(): List<CountryDbEntity> =
    this.map { CountryDbEntity(country = it) }
