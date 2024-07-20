package com.example.universities.data.network

import com.example.universities.data.model.UniversityJson
import retrofit2.Response
import retrofit2.http.GET

interface UniversitiesApi {

    @GET("search")
    suspend fun getUniversitiesData(): Response<List<UniversityJson>>
}