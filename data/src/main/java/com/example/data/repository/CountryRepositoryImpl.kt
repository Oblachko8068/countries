package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.room.CountryDao
import com.example.domain.model.Country
import com.example.domain.repository.CountriesRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countryDao: CountryDao
) : CountriesRepository {

    override fun getCountriesListLiveData(): LiveData<List<Country>> = countryDao.getListCountry()
        .map { countryDbEntityList -> countryDbEntityList.map { it.toCountryDb() } }
}