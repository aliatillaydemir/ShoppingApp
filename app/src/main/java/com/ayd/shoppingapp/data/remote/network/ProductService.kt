package com.ayd.shoppingapp.data.remote.network

import com.ayd.shoppingapp.data.model.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ProductService {
    @GET("/products")
    suspend fun getProducts(
        @QueryMap queries: Map<String,String>
    ): Response<Products>

}