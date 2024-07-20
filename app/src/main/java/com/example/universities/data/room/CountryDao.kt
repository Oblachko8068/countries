package com.example.universities.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universities.data.model.CountryDbEntity

@Dao
interface CountryDao {

    @Query("SELECT * FROM country")
    fun getListCountry(): LiveData<List<CountryDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInDb(countryDbEntity: List<CountryDbEntity>)
}