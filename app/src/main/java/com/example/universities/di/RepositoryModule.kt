package com.example.universities.di

import com.example.universities.data.repository.CountryRepositoryImpl
import com.example.universities.data.repository.DownloadRepositoryImpl
import com.example.universities.domain.repository.CountriesRepository
import com.example.universities.domain.repository.DownloadRepository
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
}