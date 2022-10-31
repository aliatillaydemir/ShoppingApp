package com.ayd.shoppingapp.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ayd.shoppingapp.R
import com.ayd.shoppingapp.databinding.FragmentAuthPagerAdapterBinding
import com.ayd.shoppingapp.databinding.FragmentAuthPagerBinding
import com.ayd.shoppingapp.ui.authentication.authenticationScreens.LoginFragment
import com.ayd.shoppingapp.ui.authentication.authenticationScreens.RegistrationFragment


class AuthPagerAdapter(
    list: ArrayList<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList = list

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {

        return fragmentList[position]

    }

}