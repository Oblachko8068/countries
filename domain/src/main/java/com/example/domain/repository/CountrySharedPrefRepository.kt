package com.example.domain.repository


interface CountrySharedPrefRepository {

    fun getSavedCountry(): String?

    fun saveCountryToSharedPref(country: String)
}