package com.example.universities.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.universities.domain.model.University

@Entity(
    tableName = "university"
)

data class UniversityDbEntity(
    @PrimaryKey val name: String,
    val webPage: String,
    val country: String,
    val alphaTwoCode: String,
    val stateProvince: String?,
) {
    fun toUniversityDb(): University =
        University(
            name = name,
            webPage = webPage,
            country = country,
            alphaTwoCode = alphaTwoCode,
            stateProvince = stateProvince
        )
}

fun UniversityJson.fromUniversityJsonToUniversityDbEntity(): UniversityDbEntity = UniversityDbEntity(
    name = this.name,
    webPage = this.web_pages[0],
    country = this.country,
    alphaTwoCode = this.alpha_two_code,
    stateProvince = this.stateProvince
)