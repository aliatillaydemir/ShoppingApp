package com.ayd.shoppingapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayd.shoppingapp.data.database.entities.BasketEntity
import com.ayd.shoppingapp.data.database.entities.ProductEntity

@Database(
    entities = [ProductEntity::class, BasketEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProductTypeConverter::class)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao


}