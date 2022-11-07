package com.ayd.shoppingapp.ui.mainScreens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayd.shoppingapp.adapters.SearchAdapter
import com.ayd.shoppingapp.data.model.Products
import com.ayd.shoppingapp.databinding.FragmentSearchBinding
import com.ayd.shoppingapp.utils.NetworkListener
import com.ayd.shoppingapp.utils.NetworkResults
import com.ayd.shoppingapp.utils.observeOnce
import com.ayd.shoppingapp.viewmodel.MainViewModel
import com.ayd.shoppingapp.viewmodel.ProductViewModel
import kotlinx.coroutines.launch


class SearchFragment : Fragment()/*, SearchView.OnQueryTextListener*/ {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { SearchAdapter() }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var productViewModel: ProductViewModel

    private lateinit var networkListener : NetworkListener

    private val args by navArgs<DetailsFragmentArgs>()

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
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        setupRecyclerView()
        readDb()

        lifecycleScope.launch {
            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(requireContext())
                .collect{ status ->
                    Log.d("NetworkListener", status.toString())

                    productViewModel.networkState = status
                    productViewModel.networkStatus()
                }
        }

        binding.imageViewError.setOnClickListener {
            if(productViewModel.networkState){

                val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(args.productDto)
                findNavController().navigate(action)

            }else{
                productViewModel.networkStatus()
            }
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("search","girdi")
                if(query != null){
                    searchDb(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Log.d("search","girdi")
                if(query != null){
                    searchDb(query)
                }else{
                    Log.d("search","nothing")
                }
                return false
            }

        })

        requestApiData()

        binding.button3.setOnClickListener {
            requestApiDataElectronic()
        }

        binding.button4.setOnClickListener {
            requestApiDataJewelery()
        }

        binding.button5.setOnClickListener {
            requestApiDataMen()
        }

        binding.button6.setOnClickListener {
            requestApiDataWomen()
        }

        return binding.root
    }


    private fun setupRecyclerView(){
        binding.searchRecyclerView.adapter = mAdapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun readDb() {
        lifecycleScope.launch{
            mainViewModel.readProduct.observeOnce(viewLifecycleOwner){ db ->
                if(db.isNotEmpty()){
                    Log.d("ProductDb","read db")
                    //mAdapter.setData(db[0].product)
                }else{
                    //requestApiData()
                }
            }
        }
    }


    private fun requestApiData(){
        Log.d("ProductDb","read api")
        mainViewModel.getProducts(productViewModel.applyQueries())
        mainViewModel.productResponse.observe(viewLifecycleOwner){response ->
            when (response) {
                is NetworkResults.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResults.Error -> {
                    loadDataCache()              //if error
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

    private fun requestApiDataElectronic(){
        Log.d("ProductDb","read api")
        mainViewModel.getProductsElectronic(productViewModel.applyQueries())
        mainViewModel.productResponse.observe(viewLifecycleOwner){response ->
            when (response) {
                is NetworkResults.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResults.Error -> {
                    //loadDataCache()              //if error
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


    private fun requestApiDataJewelery(){
        Log.d("ProductDb","read api")
        mainViewModel.getProductsJewelery(productViewModel.applyQueries())
        mainViewModel.productResponse.observe(viewLifecycleOwner){response ->
            when (response) {
                is NetworkResults.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResults.Error -> {
                    //loadDataCache()              //if error
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


    private fun requestApiDataMen(){
        Log.d("ProductDb","read api")
        mainViewModel.getProductsMen(productViewModel.applyQueries())
        mainViewModel.productResponse.observe(viewLifecycleOwner){response ->
            when (response) {
                is NetworkResults.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResults.Error -> {
                    //loadDataCache()              //if error
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


    private fun requestApiDataWomen(){
        Log.d("ProductDb","read api")
        mainViewModel.getProductsWomen(productViewModel.applyQueries())
        mainViewModel.productResponse.observe(viewLifecycleOwner){response ->
            when (response) {
                is NetworkResults.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResults.Error -> {
                    //loadDataCache()              //if error
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



    //if error!
    private fun loadDataCache(){
        lifecycleScope.launch{
            mainViewModel.readProduct.observe(viewLifecycleOwner){ db ->
                if(db.isNotEmpty()){
                    mAdapter.setData(db[0].product)
                }
            }
        }

    }



    private fun searchDb(query: String){
        val searchQuery = "%$query%"

        mainViewModel.searchDb(searchQuery).observe(this){ searchList ->

            searchList.let{
                try {
                    mAdapter.setQuery(it[0].product)
                }catch (e:Exception){
                    Toast.makeText(requireContext(),"The product you were looking for was not found.",Toast.LENGTH_SHORT).show()
                }

            }
        }
    }



}