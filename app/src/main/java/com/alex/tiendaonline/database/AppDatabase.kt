package com.alex.tiendaonline.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex.tiendaonline.dao.UserDao
import com.alex.tiendaonline.dao.ProductDao
import com.alex.tiendaonline.model.User
import com.alex.tiendaonline.model.Product
import com.alex.tiendaonline.model.CartItem

@Database(
    entities = [User::class, Product::class, CartItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tienda_online_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}