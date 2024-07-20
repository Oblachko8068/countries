package com.example.data.repository

import android.content.SharedPreferences
import com.example.domain.repository.CountrySharedPrefRepository
import javax.inject.Inject

const val SHARED_PREF_KEY = "country_key"

class CountrySharedPrefRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : CountrySharedPrefRepository {

    override fun getSavedCountry(): String? {
        return sharedPreferences.getString(SHARED_PREF_KEY, null)
    }

    override fun saveCountryToSharedPref(country: String) {
        val editor = sharedPreferences.edit()
        editor.putString(SHARED_PREF_KEY, country)
        editor.commit()
    }
}