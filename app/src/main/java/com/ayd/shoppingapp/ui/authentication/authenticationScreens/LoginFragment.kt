package com.ayd.shoppingapp.ui.authentication.authenticationScreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ayd.shoppingapp.MainActivity
import com.ayd.shoppingapp.R
import com.ayd.shoppingapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_authPagerFragment_to_productsFragment)
        }


        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}