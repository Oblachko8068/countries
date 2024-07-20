package com.example.data.repository

import com.example.data.model.fromCountrySetToCountryDbEntity
import com.example.data.model.fromUniversityJsonToUniversityDbEntity
import com.example.data.room.CountryDao
import com.example.data.room.UniversityDao
import com.example.domain.repository.DownloadRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class DownloadRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit,
    private val countryDao: CountryDao,
    private val universityDao: UniversityDao
) : DownloadRepository {

    override suspend fun fetchData() {
        val response = retrofit
            .create(com.example.data.network.UniversitiesApi::class.java)
            .getUniversitiesData()
        if (response.isSuccessful) {
            if (response.body() != null) {
                val universityList = response.body()
                val countrySet = mutableSetOf<String>()
                if (universityList != null) {
                    universityList.forEach {
                        countrySet.add(it.country)
                    }

                    val countryDbEntityList = countrySet.toList().fromCountrySetToCountryDbEntity()
                    CoroutineScope(Dispatchers.IO).launch {
                        countryDao.insertInDb(countryDbEntityList)
                    }

                    val universityDbEntityList =
                        universityList.map { it.fromUniversityJsonToUniversityDbEntity() }
                    CoroutineScope(Dispatchers.IO).launch {
                        universityDao.insertUniversityData(universityDbEntityList)
                    }
                }
            }
        }
    }
}