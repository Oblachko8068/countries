package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.Country

interface CountriesRepository {

    fun getCountriesListLiveData(): LiveData<List<Country>>
}