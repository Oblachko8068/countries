package com.example.universities.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universities.data.model.UniversityDbEntity

@Dao
interface UniversityDao {

    @Query("SELECT * FROM university")
    fun getUniversitiesByCountry(): LiveData<List<UniversityDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUniversityData(universityDbEntity: List<UniversityDbEntity>)
}