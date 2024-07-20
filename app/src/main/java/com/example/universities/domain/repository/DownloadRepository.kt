package com.example.universities.domain.repository

interface DownloadRepository {

    suspend fun fetchData()
}