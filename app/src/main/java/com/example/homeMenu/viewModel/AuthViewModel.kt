package com.example.homeMenu.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeMenu.data.AuthRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    private val userIdFlow = MutableStateFlow<String?>(null)

    private val isAdminState = MutableStateFlow<Boolean>(false)
    val isAdmin: StateFlow<Boolean> = isAdminState.asStateFlow()

    init {
        viewModelScope.launch {
            val uid = repository.getCurrentUserId() ?: repository.signInAnonymously().getOrNull()
            if (uid != null) {
                userIdFlow.value = uid
                checkIfAdmin(uid)
            } else {
                Log.d("Firestore", "Cant find uid")
            }
        }
    }

    private fun checkIfAdmin(uid: String) {
        Firebase.firestore.collection("admin")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                isAdminState.value = document.exists()
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Ошибка доступа: ${e.message}")
                isAdminState.value = false
            }
    }
}
