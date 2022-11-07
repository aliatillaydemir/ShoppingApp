package com.ayd.shoppingapp.data.local.source

import com.ayd.shoppingapp.data.database.ProductDao
import com.ayd.shoppingapp.data.database.entities.BasketEntity
import com.ayd.shoppingapp.data.database.entities.ProductEntity
import com.ayd.shoppingapp.data.model.Products
import com.ayd.shoppingapp.data.model.ProductsItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val productDao: ProductDao) {

    /** main page's data source */
    fun readProduct(): Flow<List<ProductEntity>>{
        return productDao.readProduct()
    }

    suspend fun insertProduct(productEntity: ProductEntity){
        productDao.insertProduct(productEntity)
    }

    //query
    fun searchDb(searchQuery: String): Flow<List<ProductEntity>>{
        return productDao.searchDb(searchQuery)
    }


    /** basket page's data source */
    fun readBasket(): Flow<List<BasketEntity>>{
        return productDao.readBasket()
    }

    suspend fun insertBasket(basketEntity: BasketEntity){
        productDao.insertToBasket(basketEntity)
    }

    suspend fun deleteProduct(basketEntity: BasketEntity){
        productDao.deleteProduct(basketEntity)
    }

    suspend fun clearBasket(){
        productDao.clearBasket()
    }



}