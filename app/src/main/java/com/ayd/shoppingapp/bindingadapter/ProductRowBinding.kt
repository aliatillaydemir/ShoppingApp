package com.ayd.shoppingapp.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load

class ProductRowBinding {

    companion object{

        @BindingAdapter("loadImageUrl")
        @JvmStatic
        fun loadImageUrl(imageView: ImageView, imageUrl: String){
            imageView.load(imageUrl){
                crossfade(500)
            }
        }

        @BindingAdapter("setPrice")
        @JvmStatic
        fun setPrice(textView: TextView, price: Double){
            textView.text =  price.toString()
        }

    }


}