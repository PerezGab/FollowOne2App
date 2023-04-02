package com.example.gabbinete.followone2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gabbinete.followone2.databinding.ActivityMainBinding
import com.example.gabbinete.followone2.repo.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    @Inject
    lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        drawerLayout = binding.drawerLayout
        lifecycleScope.launch { setupApp() }

        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.calendarFragment, R.id.standingsFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private suspend fun setupApp() {
        Log.d(TAG, "setupApp() is called.")
        try {
            repo.updateSeasonRaces()
            repo.updateLastRace()
            repo.updateDriverStandings()
            repo.updateConstructorStandings()
            repo.dataStoredCompleted()
        } catch (e: Exception) {
//            Snackbar.make(binding.root, "Connection Error", Snackbar.LENGTH_SHORT).show()
            Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Error is ${e.message}")
        }
    }
}