package com.ayd.shoppingapp.data.local.source

import com.ayd.shoppingapp.data.database.ProductDao
import com.ayd.shoppingapp.data.database.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val productDao: ProductDao) {

    fun readDatabase(): Flow<List<ProductEntity>>{
        return productDao.readProduct()
    }

    suspend fun insertProduct(productEntity: ProductEntity){
        productDao.insertProduct(productEntity)
    }

}