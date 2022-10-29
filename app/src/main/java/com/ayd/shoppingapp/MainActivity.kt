package com.ayd.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ayd.shoppingapp.databinding.ActivityMainBinding
import com.ayd.shoppingapp.ui.mainScreens.ProductsFragment
import com.ayd.shoppingapp.ui.mainScreens.ProfileFragment
import com.ayd.shoppingapp.ui.mainScreens.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //supportActionBar?.hide()  //ekran kaplansın, action bar kalksın.

        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment //navigation bağlantısı için.



        navController = findNavController(R.id.fragmentContainerView)
        val appBarConfig: AppBarConfiguration = AppBarConfiguration(setOf(
            R.id.productsFragment,
            R.id.searchFragment,
            R.id.profileFragment
        ))

        binding.bottomNav.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfig)



    }



    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    fun showBottomNavigation()
    {
        binding.bottomNav.visibility = View.VISIBLE
    }

    fun hideBottomNavigation()
    {
        binding.bottomNav.visibility = View.GONE
    }


}




