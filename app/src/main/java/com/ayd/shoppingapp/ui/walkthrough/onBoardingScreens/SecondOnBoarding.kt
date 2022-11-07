package com.ayd.shoppingapp.ui.walkthrough.onBoardingScreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ayd.shoppingapp.R
import com.ayd.shoppingapp.databinding.FragmentSecondOnBoardingBinding


class SecondOnBoarding : Fragment() {

    private var _binding: FragmentSecondOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondOnBoardingBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)  //view pager'a ulaşmak için findViewByid kullandım.

        binding.nextToThirdButton.setOnClickListener{
            viewPager?.currentItem = 2
        }
        binding.backToFirstButton.setOnClickListener{
            viewPager?.currentItem = 0
        }
        binding.skip2.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_authPagerFragment)
            onBoardFinish()
        }

        return binding.root
    }

    private fun onBoardFinish(){        //shared pref ile cihazda onboard ile tanıtımın bittiği boolean true olarak ifade ediliyor. Bir daha gözükmeyecek.
        val sharedPref = requireActivity().getSharedPreferences("ONBOARD", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("FINISHED",true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}