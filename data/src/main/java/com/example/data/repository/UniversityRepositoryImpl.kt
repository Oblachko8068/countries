package com.example.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.room.UniversityDao
import com.example.domain.model.University
import com.example.domain.repository.UniversityRepository
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val universityDao: UniversityDao
) : UniversityRepository {

    override fun getUniversityLiveData(country: String): LiveData<List<University>> =
        universityDao.getUniversitiesByCountry(country)
            .map { universityDbEntityList -> universityDbEntityList.map { it.toUniversityDb() } }
}