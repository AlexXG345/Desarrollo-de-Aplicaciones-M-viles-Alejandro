package com.alex.tiendaonline.repository

import android.content.Context
import com.alex.tiendaonline.database.AppDatabase
import com.alex.tiendaonline.model.Product

class ProductRepository(context: Context) {
    private val productDao = AppDatabase.getDatabase(context).productDao()

    fun getAllProducts() = productDao.getAllProducts()
    suspend fun insertProduct(product: Product) = productDao.insert(product)
    suspend fun updateProduct(product: Product) = productDao.update(product)
    suspend fun deleteProduct(product: Product) = productDao.delete(product)
}