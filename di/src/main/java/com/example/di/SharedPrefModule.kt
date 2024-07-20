package com.example.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val SHARED_PREF_NAME = "MyPref"

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
}