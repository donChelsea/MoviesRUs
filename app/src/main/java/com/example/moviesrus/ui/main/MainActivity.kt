package com.example.moviesrus.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.moviesrus.ui.discover.DiscoverFragment
import com.example.moviesrus.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.screen_discover -> {
                    loadFragment(DiscoverFragment())
                    true
                }
//                R.id.item2 -> {
//                    // Respond to navigation item 2 click
//                    true
//                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}