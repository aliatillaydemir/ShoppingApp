package com.ayd.shoppingapp.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.ayd.shoppingapp.R

class ProductRowBinding {

    companion object{

        @BindingAdapter("loadImageUrl")
        @JvmStatic
        fun loadImageUrl(imageView: ImageView, imageUrl: String){
            imageView.load(imageUrl){
                crossfade(500)
                error(R.drawable.ic_baseline_image_24)
            }
        }

        @BindingAdapter("setPrice")
        @JvmStatic
        fun setPrice(textView: TextView, price: Double){
            textView.text = "$price$"
        }

    }


}