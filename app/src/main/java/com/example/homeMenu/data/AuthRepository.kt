package com.example.homeMenu.data

interface AuthRepository {
    suspend fun signInAnonymously(): Result<String>

    fun getCurrentUserId(): String?
}
