package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainAccount : AppCompatActivity() {
    //Promise to initialise the variables at a later time, prevents nullable type
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_account_main)

        val subscriptionButton = findViewById<Button>(R.id.subButton)
        subscriptionButton.setOnClickListener {
            val intent = Intent(this, Subscription::class.java)
            startActivity(intent)
        }

        val factorButton = findViewById<Button>(R.id.FAButton)
        factorButton.setOnClickListener {
            val intent = Intent(this, FactorAuthentication::class.java)
            startActivity(intent)
        }

        val removeButton = findViewById<Button>(R.id.removeButton)
        removeButton.setOnClickListener {
            val intent = Intent(this, RemoveAccount::class.java)
            startActivity(intent)
        }

//        change name of the class 'MainActivity'
        val backButton = findViewById<Button>(R.id.backButton4)
        backButton.setOnClickListener {
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }// Set up the DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)
        // Set up the ActionBarDrawerToggle with hamburger icon
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open, //for accessibility
            R.string.close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView = findViewById(R.id.nav_view)
        navView.bringToFront()
        navView.setNavigationItemSelectedListener {
            // Handle menu item clicks here
            when (it.itemId) {
                R.id.home_page -> {
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                }
                R.id.view_wallet -> {
                    val intent = Intent(this, ViewWallet::class.java)
                    startActivity(intent)
                }
                R.id.send_funds -> {
                    val intent = Intent(this, SendFunds::class.java)
                    startActivity(intent)
                }
                R.id.view_keys -> {
                    val intent = Intent(this, ViewKey::class.java)
                    startActivity(intent)
                }
                R.id.logout -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}