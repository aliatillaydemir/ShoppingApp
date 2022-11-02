package com.ayd.shoppingapp.ui.mainScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayd.shoppingapp.adapters.BasketProductAdapter
import com.ayd.shoppingapp.databinding.FragmentBasketBinding
import com.ayd.shoppingapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private val mAdapter: BasketProductAdapter by lazy { BasketProductAdapter() }
    private val mainViewModel: MainViewModel by viewModels()

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}