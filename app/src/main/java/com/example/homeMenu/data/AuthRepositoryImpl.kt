package com.example.homeMenu.data

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl() : AuthRepository {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override suspend fun signInAnonymously(): Result<String> {
        return try {
            val authResult = auth.signInAnonymously().await()
            val userId = authResult.user?.uid
            if (userId != null) {
                Result.success(userId)
            } else {
                Result.failure(Exception("User ID is null"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getCurrentUserId(): String? = auth.currentUser?.uid
}
