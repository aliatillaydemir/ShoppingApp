package com.ayd.shoppingapp.utils

sealed class NetworkResults<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data:T):NetworkResults<T>(data)
    class Loading<T>: NetworkResults<T>()
    class Error<T>(message: String?, data: T? = null): NetworkResults<T>(data,message)
}