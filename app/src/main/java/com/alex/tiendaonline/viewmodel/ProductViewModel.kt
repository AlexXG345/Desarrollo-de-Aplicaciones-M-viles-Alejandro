package com.alex.tiendaonline.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alex.tiendaonline.model.Product
import com.alex.tiendaonline.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductRepository(application)

    // Expone una lista observable de productos
    val allProducts = repository.getAllProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Operaciones CRUD
    fun insert(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun update(product: Product) = viewModelScope.launch {
        repository.updateProduct(product)
    }

    fun delete(product: Product) = viewModelScope.launch {
        repository.deleteProduct(product)
    }
}