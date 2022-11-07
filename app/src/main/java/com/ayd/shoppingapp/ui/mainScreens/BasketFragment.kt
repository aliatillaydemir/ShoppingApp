package com.ayd.shoppingapp.ui.mainScreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayd.shoppingapp.MainActivity
import com.ayd.shoppingapp.adapters.BasketProductAdapter
import com.ayd.shoppingapp.databinding.FragmentBasketBinding
import com.ayd.shoppingapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter: BasketProductAdapter by lazy { BasketProductAdapter() }

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBasketBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this

        //adapter bindings
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter


        //setRecyclerView
        binding.basketRecyclerView.adapter = mAdapter
        binding.basketRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.purchaseButton.setOnClickListener {
            mainViewModel.clearBasket()  //buy everything
            if(mainViewModel.readBasket.value?.isNotEmpty()!!){
                Snackbar.make(binding.root,"your purchase was successful!",Snackbar.LENGTH_SHORT).setAction("I got it!"){}.show()
            }else{
                Snackbar.make(binding.root,"your basket is empty!",Snackbar.LENGTH_SHORT).setAction("I got it!"){}.show()
            }
        }

    }


/*    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }*/


/*    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).hideBottomNavigation()
    }*/


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}