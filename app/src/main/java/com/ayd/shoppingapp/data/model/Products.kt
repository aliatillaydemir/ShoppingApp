package com.ayd.shoppingapp.data.model

import com.google.gson.annotations.SerializedName


//class Products : ArrayList<ProductsItem>()

data class Products(
    val results: List<ProductsItem>
)