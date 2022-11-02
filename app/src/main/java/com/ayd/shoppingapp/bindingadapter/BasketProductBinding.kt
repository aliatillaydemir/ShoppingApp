package com.ayd.shoppingapp.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ayd.shoppingapp.adapters.BasketProductAdapter
import com.ayd.shoppingapp.data.database.entities.BasketEntity

class BasketProductBinding {

    companion object{

        @BindingAdapter("viewVisibility","setData", requireAll = false)
        @JvmStatic
        fun setDataVisibility(view: View, basketEntity: List<BasketEntity>?, mAdapter: BasketProductAdapter?){

            if(basketEntity.isNullOrEmpty()){
                when(view){
                    is ImageView -> {
                        view.visibility = View.VISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.VISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.INVISIBLE
                    }
                }
            } else{
                when(view){
                    is ImageView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.VISIBLE
                        mAdapter?.setData(basketEntity)
                    }
                }
            }
        }



    }

}