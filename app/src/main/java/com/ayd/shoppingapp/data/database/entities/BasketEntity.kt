package com.ayd.shoppingapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayd.shoppingapp.data.model.ProductsItem
import com.ayd.shoppingapp.utils.Constants.Companion.BASKET_TABLE

@Entity(tableName = BASKET_TABLE)
class BasketEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var productsItem: ProductsItem
)
