package com.ayd.shoppingapp.ui.walkthrough

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ayd.shoppingapp.databinding.FragmentViewPagerBinding
import com.ayd.shoppingapp.ui.walkthrough.onBoardingScreens.FirstOnBoarding
import com.ayd.shoppingapp.ui.walkthrough.onBoardingScreens.SecondOnBoarding
import com.ayd.shoppingapp.ui.walkthrough.onBoardingScreens.ThirdOnBoarding

class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstOnBoarding(),
            SecondOnBoarding(),
            ThirdOnBoarding()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}