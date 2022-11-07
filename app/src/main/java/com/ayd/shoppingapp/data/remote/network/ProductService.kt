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



    @GET("/products/category/electronics")
    suspend fun getElectronics(
        @QueryMap queries: Map<String,String>
    ): Response<Products>

    @GET("/products/category/jewelery")
    suspend fun getJewelery(
        @QueryMap queries: Map<String,String>
    ): Response<Products>

    @GET("/products/category/men's clothing")
    suspend fun getMenClothing(
        @QueryMap queries: Map<String,String>
    ): Response<Products>

    @GET("/products/category/women's clothing")
    suspend fun getWomenClothing(
        @QueryMap queries: Map<String,String>
    ): Response<Products>

}