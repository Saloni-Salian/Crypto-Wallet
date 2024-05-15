package com.example.cryptowallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BitcoinPrivateKey : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitcoin_private_key)

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            //change your ip address to your local computer's ip address before running
            .baseUrl("http://192.168.1.114:8080/")
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


                                val bitcoin_privatekey = findViewById<TextView>(R.id.BTCPrivateKey)
                                val privatekey = detailsArray[3]
                                bitcoin_privatekey.text= ("BTC Private Key : "+privatekey)
                            }


//
                        }
                    }
                } else {

                }
            }

            override fun onFailure(call: Call<HashMap<String, Array<String>>>, t: Throwable) {
                Log.e("MainActivity", "Failed to make API call: ${t.message}")
            }
        })

        val backButton = findViewById<Button>(R.id.backButton11)
        backButton.setOnClickListener {
            finish()//finish the activity and go back to the previous one
        }
    }


}