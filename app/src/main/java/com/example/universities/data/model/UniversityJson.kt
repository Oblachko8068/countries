package com.example.universities.data.model

import com.google.gson.annotations.SerializedName

data class UniversityJson(
    val name: String,
    val domains: List<String>,
    val web_pages: List<String>,
    val country: String,
    val alpha_two_code: String,
    @SerializedName("state-province") val stateProvince: String?,
)
