package com.example.currencytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.children
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.currencytracker.databinding.ActivityMainBinding
import com.example.currencytracker.extensions.hide
import com.example.currencytracker.extensions.show
import com.example.currencytracker.feature.settings.SortSettings
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    private val binding by viewBinding(ActivityMainBinding::bind)
    val component by lazy { App.appComponent.mainActivityComponent().create() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
    }

    fun showBottomNavigation(){
        binding.currencyTrackerBottomNavigationView.show()
    }

    fun hideBottomNavigation() {
        binding.currencyTrackerBottomNavigationView.hide()
    }

    private fun setupBottomNavigation() {
        navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment).navController
        bottomNavigationView = binding.currencyTrackerBottomNavigationView
        bottomNavigationView.setupWithNavController(navController)
    }

}