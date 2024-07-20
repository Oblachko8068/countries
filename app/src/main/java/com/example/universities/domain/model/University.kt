package com.example.universities.domain.model

data class University(
    val name: String,
    val webPage: String,
    val country: String,
    val alphaTwoCode: String,
    val stateProvince: String?,
)