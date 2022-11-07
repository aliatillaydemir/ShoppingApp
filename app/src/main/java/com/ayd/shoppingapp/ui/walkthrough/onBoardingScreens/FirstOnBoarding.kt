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
import com.ayd.shoppingapp.databinding.FragmentFirstOnBoardingBinding

class FirstOnBoarding : Fragment() {

    private var _binding: FragmentFirstOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFirstOnBoardingBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)  //view pager'a ulaşmak için findViewByid kullandım. Çünkü farklı bir fragment'ın xml dosyası.

        binding.nextToSecondButton.setOnClickListener{
            viewPager?.currentItem = 1           //bu sayfayı geçince 2. ekrana(Second Screen'e) gidilir. Eleman pozisyonları 0-1-2 şeklinde ifade edililir.
        }

        binding.skip1.setOnClickListener {
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