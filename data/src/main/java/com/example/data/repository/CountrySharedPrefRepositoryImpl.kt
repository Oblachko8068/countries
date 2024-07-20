package com.example.data.repository

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.domain.repository.CountrySharedPrefRepository
import javax.inject.Inject

class CountrySharedPrefRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : CountrySharedPrefRepository {

    override fun getSavedCountry(): String? {
        return sharedPreferences.getString("country", null)
    }

    override fun saveCountryToSharedPref(country: String) {
        val editor = sharedPreferences.edit()
        editor.putString("country", country)
        editor.commit()
    }

    override fun deleteCountry() {
        val editor = sharedPreferences.edit()
        editor.remove("country")
        editor.commit()
    }
}