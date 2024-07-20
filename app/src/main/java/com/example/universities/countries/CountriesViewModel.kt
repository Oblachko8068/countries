package com.example.universities.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.DownloadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val downloadRepository: DownloadRepository,
) : ViewModel() {

    //private var countryLiveData: LiveData<List<Country>>

    fun init1() {
        viewModelScope.launch {
            downloadRepository.fetchData()
        }
    }
}