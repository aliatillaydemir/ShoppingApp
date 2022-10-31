package com.ayd.shoppingapp.ui.mainScreens

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayd.shoppingapp.MainActivity
import com.ayd.shoppingapp.viewmodel.MainViewModel
import com.ayd.shoppingapp.R
import com.ayd.shoppingapp.adapters.ProductAdapter
import com.ayd.shoppingapp.databinding.FragmentProductsBinding
import com.ayd.shoppingapp.utils.NetworkResults
import com.ayd.shoppingapp.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { ProductAdapter() }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var productViewModel: ProductViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProductsBinding.inflate(inflater, container, false)

        setupRecyclerView()
        requestApiData()

        return binding.root
    }


    private fun requestApiData(){
        mainViewModel.getProducts(productViewModel.applyQueries())
        mainViewModel.productResponse.observe(viewLifecycleOwner){response ->
            when (response) {
                is NetworkResults.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResults.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResults.Loading -> {

                }
            }
        }
    }




    private fun setupRecyclerView(){
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        //binding.recyclerview.layoutManager = GridLayoutManager(requireContext(),2)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.actionbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_basket -> {
                        findNavController().navigate(R.id.basketFragment)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }




    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).showBottomNavigation()
    }

}