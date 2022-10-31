package com.ayd.shoppingapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProductTypeConverter::class)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao


}