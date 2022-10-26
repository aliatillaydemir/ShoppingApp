package com.ayd.shoppingapp.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ayd.shoppingapp.R
import com.ayd.shoppingapp.databinding.FragmentAuthPagerBinding
import com.ayd.shoppingapp.ui.authentication.authenticationScreens.LoginFragment
import com.ayd.shoppingapp.ui.authentication.authenticationScreens.RegistrationFragment
import com.google.android.material.tabs.TabLayoutMediator


class AuthPagerFragment : Fragment() {

    private var _binding: FragmentAuthPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAuthPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            LoginFragment(),
            RegistrationFragment()
        )

        val adapter = AuthPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPagerAuth.adapter = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPagerAuth){tabLayout, position ->
            when(position){
                0 -> tabLayout.text = "LOGIN"
                1 -> tabLayout.text = "REGISTRATION"
            }
        }.attach()


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}