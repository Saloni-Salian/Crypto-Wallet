package com.example.cryptowallet

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class SendBitcoin : AppCompatActivity(){
    private lateinit var infofordatabase:ArrayList<String>
    //Promise to initialise the variables at a later time, prevents nullable type
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_send_bitcoin)
        val enterButton = findViewById<Button>(R.id.Enter)
        enterButton.setOnClickListener{
            infofordatabase = createListOfData()
            //Connect to the database and send the data to the database
            Toast.makeText(this@SendBitcoin,infofordatabase.toString(),Toast.LENGTH_SHORT).show()
            //Create an if-else case to check if anything went wrong
            val intent = Intent(this, TransactionCompleted::class.java)
            startActivity(intent)
        }

        val backButton = findViewById<Button>(R.id.goback)
        backButton.setOnClickListener {
            finish()//finish the activity and go back to the previous one
        }
        // Set up the DrawerLayout
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
            when(it.itemId){
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
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createListOfData(): ArrayList<String> {
        val list = arrayListOf<String>()
        val address: EditText = findViewById(R.id.editTextTextPersonName2)
        val inputAddress: String = address.text.toString()
        val amount: EditText = findViewById(R.id.editTextTextPersonName3)
        val inputAmount: String = amount.text.toString()
        list.add(inputAddress)
        list.add(inputAmount)
        return list
    }
}