package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.model.University

interface UniversityRepository {

    fun getUniversityLiveData(country: String): LiveData<List<University>>
}