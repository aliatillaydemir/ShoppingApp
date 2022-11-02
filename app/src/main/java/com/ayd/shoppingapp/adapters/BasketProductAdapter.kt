package com.ayd.shoppingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayd.shoppingapp.data.database.entities.BasketEntity
import com.ayd.shoppingapp.databinding.BasketRowLayoutBinding
import com.ayd.shoppingapp.utils.ProductDiffUtil

class BasketProductAdapter: RecyclerView.Adapter<BasketProductAdapter.BasketViewHolder>() {

    private var basketProduct = emptyList<BasketEntity>()

    class BasketViewHolder(private val binding: BasketRowLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun bindingEntity(basketEntity: BasketEntity){
                binding.basketEntity = basketEntity
                binding.executePendingBindings()
            }

        companion object{
            fun from(parent: ViewGroup): BasketViewHolder{
                val layoutInf = LayoutInflater.from(parent.context)
                val binding = BasketRowLayoutBinding.inflate(layoutInf,parent,false)
                return BasketViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val selectedProduct = basketProduct[position]
        holder.bindingEntity(selectedProduct)
    }

    override fun getItemCount(): Int {
        return basketProduct.size
    }

    fun setData(newBasketProduct: List<BasketEntity>){
        val basketDiffUtil = ProductDiffUtil(basketProduct, newBasketProduct)
        val diffUtilProduct = DiffUtil.calculateDiff(basketDiffUtil)
        basketProduct = newBasketProduct
        diffUtilProduct.dispatchUpdatesTo(this)
    }


}