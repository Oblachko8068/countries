package com.example.universities.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Country
import com.example.domain.repository.CountriesRepository
import com.example.domain.repository.DownloadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val downloadRepository: DownloadRepository,
    private val countryRepository: CountriesRepository
) : ViewModel() {

    private var countryLiveData: LiveData<List<Country>> = countryRepository.getCountriesListLiveData()
    private val mediatorLiveData = MediatorLiveData<List<Country>>()

    init {
        mediatorLiveData.addSource(countryLiveData) {
            mediatorLiveData.value = it
        }
        viewModelScope.launch {
            downloadRepository.fetchData()
        }
    }

    fun getMediatorLiveData(): MediatorLiveData<List<Country>> = mediatorLiveData

    fun getFilteredCountries(): List<Country> {
        val countries = countryLiveData.value ?: emptyList()
        val searchQuery = _searchViewLiveData.value
        return if (!searchQuery.isNullOrEmpty()) {
            countries.filter {
                it.country.contains(searchQuery, ignoreCase = true)
            }
        } else {
            countries
        }
    }

    fun setSearchText(searchText: String) {
        _searchViewLiveData.value = searchText
    }

    companion object {
        private val _searchViewLiveData = MutableLiveData<String>()
        val searchTextLiveData: LiveData<String> = _searchViewLiveData
    }
}