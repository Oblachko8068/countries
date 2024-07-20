package com.example.di

import com.example.data.repository.CountryRepositoryImpl
import com.example.data.repository.DownloadRepositoryImpl
import com.example.domain.repository.CountriesRepository
import com.example.domain.repository.DownloadRepository
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

    /*@Binds
    @Singleton
    abstract fun bindCountryRepository(
        countriesRepository: CountryRepositoryImpl
    ): CountriesRepository*/
}