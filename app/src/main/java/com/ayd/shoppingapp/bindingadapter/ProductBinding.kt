package com.ayd.shoppingapp.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ayd.shoppingapp.data.database.ProductEntity
import com.ayd.shoppingapp.data.model.Products
import com.ayd.shoppingapp.utils.NetworkResults

class ProductBinding {

    companion object{

        @BindingAdapter("readApi", "readDb", requireAll = true)
        @JvmStatic
        fun errorImage(
            imageView: ImageView,
            apiResponse: NetworkResults<Products>?,
            db: List<ProductEntity>?
        ){
            if(apiResponse is NetworkResults.Error && db.isNullOrEmpty()){
                imageView.visibility = View.VISIBLE
            } else if(apiResponse is NetworkResults.Loading){
                imageView.visibility = View.INVISIBLE
            }else if(apiResponse is NetworkResults.Success){
                imageView.visibility = View.INVISIBLE
            }

        }

        @BindingAdapter("readApiText", "readDbText", requireAll = true)
        @JvmStatic
        fun errorText(
            textView: TextView,
            apiResponse: NetworkResults<Products>?,
            db: List<ProductEntity>?
        ){
            if(apiResponse is NetworkResults.Error && db.isNullOrEmpty()){
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            } else if(apiResponse is NetworkResults.Loading){
                textView.visibility = View.INVISIBLE
            }else if(apiResponse is NetworkResults.Success){
                textView.visibility = View.INVISIBLE
            }

        }

    }

}