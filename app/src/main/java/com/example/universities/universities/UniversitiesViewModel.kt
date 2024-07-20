package com.example.universities.universities

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.University
import com.example.domain.repository.CountrySharedPrefRepository
import com.example.domain.repository.UniversityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UniversitiesViewModel @Inject constructor(
    private val universityRepository: UniversityRepository,
) : ViewModel() {

    private val mediatorLiveData = MediatorLiveData<List<University>>()
    private lateinit var universityLiveData: LiveData<List<University>>

    fun loadDataByCountry(country: String) {
        universityLiveData = universityRepository.getUniversityLiveData(country)
        mediatorLiveData.addSource(universityLiveData) {
            mediatorLiveData.value = it
        }
    }

    fun getMediatorLiveData(): MediatorLiveData<List<University>> = mediatorLiveData

    fun getFilteredUniversities(): List<University> {
        val universities = universityLiveData.value ?: emptyList()
        val searchQuery = _searchViewUniversityLiveData.value
        return if (!searchQuery.isNullOrEmpty()) {
            universities.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        } else {
            universities
        }
    }

    fun setSearchText(searchText: String) {
        _searchViewUniversityLiveData.value = searchText
    }

    companion object {
        private val _searchViewUniversityLiveData = MutableLiveData<String>()
        val searchUniversityLiveData: LiveData<String> = _searchViewUniversityLiveData
    }
}