package com.example.universities.di

import android.content.Context
import androidx.room.Room
import com.example.universities.data.room.AppDatabase
import com.example.universities.data.room.CountryDao
import com.example.universities.data.room.UniversityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCountryDao(appDatabase: AppDatabase): CountryDao = appDatabase.getCountryDao()

    @Provides
    @Singleton
    fun provideUniversityDao(appDatabase: AppDatabase): UniversityDao = appDatabase.getUniversityDao()

}