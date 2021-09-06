package com.taufik.tixidgithubuser.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.taufik.tixidgithubuser.R
import com.taufik.tixidgithubuser.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        setNavHostFragment()
    }

    private fun initActionBar() {
        supportActionBar?.elevation = 0F
    }

    private fun setNavHostFragment() {
        binding.apply {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            navController = navHostFragment.findNavController()
            setSupportActionBar(toolbarMain)
            setupActionBarWithNavController(navController)
        }
    }
}