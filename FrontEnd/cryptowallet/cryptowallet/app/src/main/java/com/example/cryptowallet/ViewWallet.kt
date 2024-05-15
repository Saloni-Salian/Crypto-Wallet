package com.example.cryptowallet
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import android.view.MenuItem
import com.google.gson.GsonBuilder
import retrofit2.http.GET
import kotlin.random.Random

class ViewWallet : AppCompatActivity() {
    //Promise to initialise the variables at a later time, prevents nullable type
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_wallet)
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.114:8080/")//IP4 address of the user
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        // Create an instance of the API interface using the Retrofit instance
        val api = retrofit.create(WalletDetailsApi::class.java)
        api.getWalletDetails().enqueue(object : Callback<HashMap<String, Array<String>>> {
            override fun onResponse(call: Call<HashMap<String, Array<String>>>, response: Response<HashMap<String, Array<String>>>) {
                val walletDetails = response.body()
                if (walletDetails != null) {
                    Log.d("MainActivity", "Total balance: ${walletDetails["Total balance"]?.get(0)}")
                    for ((name, detailsArray) in walletDetails) {
                        if (name != "Total balance") {
                            if (name == "Bitcoin"){
                                val bitcoinbalance = findViewById<TextView>(R.id.bitcoinPriceText)
                                val balance = detailsArray[1]
                                bitcoinbalance.text= ("BTC Balance: " +balance)
                            }
                            if (name == "Ethereum"){
                                val ethereumbalance = findViewById<TextView>(R.id.EthereumAsset)
                                val balance = detailsArray[1]
                                ethereumbalance.text= ("ETH Balance: " +balance)
                            }

//
                        }
                    }
                } else {
                    Log.d("MainActivity", "Response body is null")
                }
            }

            override fun onFailure(call: Call<HashMap<String, Array<String>>>, t: Throwable) {
                Log.e("MainActivity", "Failed to make API call: ${t.message}")
            }
        })

        val bitcoin_price = Random.nextDouble(1.01, 500.50)
        val newpriceb= String.format("%.2f", bitcoin_price)
        val bitcoinprice = findViewById<TextView>(R.id.BTCPrice)
        bitcoinprice.text= "BTC : "+newpriceb.toString()
        val ethereum_price = Random.nextDouble(1.01, 500.50)
        val newpricee= String.format("%.2f", ethereum_price)
        val ethereumprice = findViewById<TextView>(R.id.ETHPrice)
        ethereumprice.text= "ETH : "+newpricee.toString()

        val bitcoin = findViewById<Button>(R.id.BitcoinButton)
        bitcoin.setOnClickListener {
            val intent = Intent(this, BitcoinInfo::class.java)
            startActivity(intent)

        }
        val ethereum = findViewById<Button>(R.id.EthereumButton)
        ethereum.setOnClickListener {
            val intent = Intent(this, EthereumInfo::class.java)
            startActivity(intent)

        }
        val backButton = findViewById<Button>(R.id.backButton10)
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
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

interface WalletDetailsApi {
    @GET("/walletDetails")
    fun getWalletDetails(): Call<HashMap<String, Array<String>>>
}