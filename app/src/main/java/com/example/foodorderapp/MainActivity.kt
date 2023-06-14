package com.example.foodorderapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.foodorderapp.databinding.ActivityMainBinding
import com.example.foodorderapp.util.info.InfoBottomSheetDialog
import com.example.foodorderapp.util.info.InfoDialogArgs
import com.example.foodorderapp.util.info_event.InfoEventCollector
import com.example.foodorderapp.util.info_event.InfoEventCollectorImpl
import com.example.foodorderapp.util.type_alias.RString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),  InfoEventCollector by InfoEventCollectorImpl() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("MissingPermission")
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                viewModel.fetchCurrentLocation(usePreciseLocation = true)
            }

            permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                viewModel.fetchCurrentLocation(usePreciseLocation = false)
            }

            else -> {
                // No location access granted.
                val args =
                    InfoDialogArgs(
                        title = getString(R.string.something_went_wrong),
                        message = getString(RString.location_permission_error),
                        buttonText = getString(RString.settings)
                    )

                InfoBottomSheetDialog
                    .newInstance(args)
                    .show(this.supportFragmentManager, InfoBottomSheetDialog.TAG)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
        locationPermissionRequest.launch(locationPermissions)
    }

    private fun setupBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.mainNavContainer
        ) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.setOnItemReselectedListener { item ->
            // Pop everything up to the reselected item
            val selectedMenuItemNavGraph = navController.graph.findNode(item.itemId) as? NavGraph
            selectedMenuItemNavGraph?.let {
                navController.popBackStack(it.startDestinationId, inclusive = false)
            }
        }
    }

    companion object {
        private val locationPermissions = arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
    }

    fun changeTab(navId: Int) {
        binding.bottomNav.selectedItemId = navId
    }
}