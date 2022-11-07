package com.ayd.shoppingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayd.shoppingapp.data.database.entities.ProductEntity
import com.ayd.shoppingapp.data.model.Products
import com.ayd.shoppingapp.data.model.ProductsItem
import com.ayd.shoppingapp.databinding.SearchRowLayoutBinding
import com.ayd.shoppingapp.utils.ProductDiffUtil

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var products = emptyList<ProductsItem>()

    class SearchViewHolder(private val binding: SearchRowLayoutBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(result: ProductsItem){
            binding.resultItem = result               //product_row_layout'taki <variable> name
            binding.executePendingBindings()  //update data inside layout change whatever
        }

        companion object{
            fun from(parent: ViewGroup): SearchViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchRowLayoutBinding.inflate(layoutInflater,parent,false)
                return SearchViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        return SearchViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val currentProduct = products[position]
        holder.bind(currentProduct)

    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setData(newData: Products){
        val productDiffUtil = ProductDiffUtil(products,newData)
        val diffUtilResult = DiffUtil.calculateDiff(productDiffUtil)

        products = newData
        //notifyDataSetChanged() -> bunun yerine daha performanslı olan diffUtil classını yarattık, onu kullanacağız.

        diffUtilResult.dispatchUpdatesTo(this) // bütün listeleri değil, listedeki değişen elemanları fark eder.
    }



    fun setQuery(newData: Products){
        val productDiffUtil = ProductDiffUtil(products,newData)
        val diffUtilResult = DiffUtil.calculateDiff(productDiffUtil)

        products = newData
        //notifyDataSetChanged() -> bunun yerine daha performanslı olan diffUtil classını yarattık, onu kullanacağız.

        diffUtilResult.dispatchUpdatesTo(this) // bütün listeleri değil, listedeki değişen elemanları fark eder.
    }


}