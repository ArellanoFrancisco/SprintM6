package com.example.sprintm6.View

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.sprintm6.R
import com.example.sprintm6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mbinding.root)

        setSupportActionBar(mbinding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

       /* mbinding.fab.setOnClickListener { view ->
            val currentFragmentId = navController.currentDestination?.id
            if (currentFragmentId == R.id.FirstFragment) {
                Log.d("fav", currentFragmentId.toString())
                Snackbar.make(view, R.string.snack, Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.fab).setAction("Action", null).show()
            } else if (currentFragmentId == R.id.SecondFragment) {
                Log.d("fav", "Segundo Fragmento")
                val secondFragment = supportFragmentManager.findFragmentById(R.id.SecondFragment) as? SecondFragment
                val datos = secondFragment?.obtenerdatos()
                if (datos != null) {
                    val (phoneid, phoneName) = datos
                    secondFragment.enviarCorreo(phoneid, phoneName)
                } else {
                    Log.d("fav", "Datos nulos")
                }
                Log.d("fav","Segundo Fragmento")

            }
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}