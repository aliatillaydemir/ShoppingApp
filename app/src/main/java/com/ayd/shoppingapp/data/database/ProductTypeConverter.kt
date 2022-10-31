package com.ayd.shoppingapp.data.database

import androidx.room.TypeConverter
import com.ayd.shoppingapp.data.model.Products
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun productToString(product: Products): String{
        return gson.toJson(product)
    }

    @TypeConverter
    fun stringToProduct(data: String): Products{
        val typeOfList = object: TypeToken<Products>(){}.type
        return gson.fromJson(data,typeOfList)
    }


}