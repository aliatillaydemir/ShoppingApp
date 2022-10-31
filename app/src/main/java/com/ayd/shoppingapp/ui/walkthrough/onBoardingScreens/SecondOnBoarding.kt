package com.ayd.shoppingapp.ui.walkthrough.onBoardingScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}