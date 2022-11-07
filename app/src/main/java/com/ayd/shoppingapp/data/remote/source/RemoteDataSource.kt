package com.ayd.shoppingapp.data.remote.source

import com.ayd.shoppingapp.data.model.Products
import com.ayd.shoppingapp.data.remote.network.ProductService
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val productService: ProductService
) {

    suspend fun getProducts(queries: Map<String,String>): Response<Products>{
        return productService.getProducts(queries)
    }

    suspend fun getElectronics(queries: Map<String,String>): Response<Products>{
        return productService.getElectronics(queries)
    }

    suspend fun getJewelery(queries: Map<String,String>): Response<Products>{
        return productService.getJewelery(queries)
    }

    suspend fun getMenClothing(queries: Map<String,String>): Response<Products>{
        return productService.getMenClothing(queries)
    }

    suspend fun getWomenClothing(queries: Map<String,String>): Response<Products>{
        return productService.getWomenClothing(queries)
    }

}