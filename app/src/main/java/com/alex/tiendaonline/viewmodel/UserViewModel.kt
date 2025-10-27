package com.alex.tiendaonline.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alex.tiendaonline.model.User
import com.alex.tiendaonline.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository(application)

    fun login(email: String, password: String, onSuccess: (User) -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            val user = repository.login(email, password)
            if (user != null) onSuccess(user) else onError()
        }
    }

    fun register(user: User, onSuccess: () -> Unit) {
        viewModelScope.launch {
            repository.register(user)
            onSuccess()
        }
    }
}