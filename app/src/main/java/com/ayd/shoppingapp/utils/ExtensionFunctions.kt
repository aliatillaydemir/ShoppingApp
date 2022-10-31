package com.ayd.shoppingapp.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>){
//normalde uygulama ilk yüklendiğinde api sonrası db de okunuyordu, artık sadece api okunacak.
    observe(lifecycleOwner, object: Observer<T> {
        override fun onChanged(t:T?){
            removeObserver(this)
            observer.onChanged(t)
        }
    })


}