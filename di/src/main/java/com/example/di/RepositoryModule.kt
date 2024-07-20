package com.example.di

import com.example.data.repository.CountryRepositoryImpl
import com.example.data.repository.CountrySharedPrefRepositoryImpl
import com.example.data.repository.DownloadRepositoryImpl
import com.example.data.repository.UniversityRepositoryImpl
import com.example.domain.repository.CountriesRepository
import com.example.domain.repository.CountrySharedPrefRepository
import com.example.domain.repository.DownloadRepository
import com.example.domain.repository.UniversityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDownloadRepository(
        downloadRepository: DownloadRepositoryImpl
    ): DownloadRepository

    @Binds
    @Singleton
    abstract fun bindCountryRepository(
        countriesRepository: CountryRepositoryImpl
    ): CountriesRepository

    @Binds
    @Singleton
    abstract fun bindUniversityRepository(
        universityRepository: UniversityRepositoryImpl
    ): UniversityRepository

    @Binds
    @Singleton
    abstract fun bindCountrySharedPrefRepository(
        countrySharedPrefRepository: CountrySharedPrefRepositoryImpl
    ): CountrySharedPrefRepository
}