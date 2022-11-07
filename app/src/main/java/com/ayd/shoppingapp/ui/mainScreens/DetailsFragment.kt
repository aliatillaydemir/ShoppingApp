package com.ayd.shoppingapp.ui.mainScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.ayd.shoppingapp.R
import com.ayd.shoppingapp.data.database.entities.BasketEntity
import com.ayd.shoppingapp.data.model.ProductsItem
import com.ayd.shoppingapp.databinding.FragmentDetailsBinding
import com.ayd.shoppingapp.databinding.FragmentProductsBinding
import com.ayd.shoppingapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)


        binding.imageView.load(args.productDto.image)
        binding.titleTextView.text = args.productDto.title
        binding.priceTextView.text = args.productDto.price.toString()
        binding.descriptionTextView.text = args.productDto.description
        binding.categoryTextView.text = args.productDto.category
        binding.rateTextView.text = args.productDto.rating?.rate.toString()
        binding.countTextView.text = args.productDto.rating?.count.toString()



        binding.addButton.setOnClickListener {
            addToBasket()
        }


        return binding.root
    }



    private fun addToBasket() {
        val basketEntity =
            BasketEntity(
                0,
                args.productDto
            )
        mainViewModel.insertBasket(basketEntity)
            Snackbar.make(binding.root,"the product added",Snackbar.LENGTH_SHORT).setAction("ok"){}
            .show()

            //Toast.makeText(requireContext(),"the product added",Toast.LENGTH_SHORT).show()
    }


}