package com.ayd.shoppingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayd.shoppingapp.data.model.Products
import com.ayd.shoppingapp.data.model.ProductsItem
import com.ayd.shoppingapp.databinding.ProductsRowLayoutBinding
import com.ayd.shoppingapp.utils.ProductDiffUtil

class ProductAdapter:RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var products = emptyList<ProductsItem>()

    class MyViewHolder(private val binding: ProductsRowLayoutBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(result: ProductsItem){
            binding.result = result               //product_row_layout'taki <variable> name
            binding.executePendingBindings()  //update data inside layout change whatever
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductsRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentProduct = products[position]
        holder.bind(currentProduct)

    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setData(newData: Products){
        val recipesDiffUtil = ProductDiffUtil(products,newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)

        products = newData
        //notifyDataSetChanged() -> bunun yerine daha performanslı olan diffUtil classını yarattık, onu kullanacağız.

        diffUtilResult.dispatchUpdatesTo(this) // bütün listeleri değil, listedeki değişen elemanları fark eder.
    }


}