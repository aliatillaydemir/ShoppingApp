package com.ayd.shoppingapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayd.shoppingapp.data.model.Products
import com.ayd.shoppingapp.utils.Constants.Companion.PRODUCT_TABLE

@Entity(tableName = PRODUCT_TABLE)
class ProductEntity(
    var product: Products
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}