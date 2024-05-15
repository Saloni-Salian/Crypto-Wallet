package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.text.SimpleDateFormat
import java.util.*

class HistoryPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_page)

        val forgetButton = findViewById<Button>(R.id.go_back)

        forgetButton.setOnClickListener {
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            //change your ip address to your local computer's ip address before running
            .baseUrl("http://192.168.1.114:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val displaythree = findViewById<TextView>(R.id.textView9)
        val api = retrofit.create(TransactionHistoryApi::class.java)

        api.getTransactionHistory().enqueue(object : Callback<List<Array<String>>> {
            override fun onResponse(call: Call<List<Array<String>>>, response: Response<List<Array<String>>>) {
                val transactionHistory = response.body()
//
                if (transactionHistory != null) {
                    for (transactionDetails in transactionHistory) {
                        displaythree.append("Transaction id: " +transactionDetails[0]+ "\n" + "Date: "+ transactionDetails[1]+  "\n" + "Crypto:  "+ transactionDetails[2]+ "\n"+ "Receiver's Public Address: "+ transactionDetails[4] +"\n"+ "Sender's Public Address: "+  transactionDetails[5] + "\n"+ "Amount: "+ transactionDetails[6] + "\n" +"------------------------------------------------------------------------------------------   " +"\n" )

                        displaythree.movementMethod = ScrollingMovementMethod()

                    }
                } else {
                    Log.d("MainActivity", "Response body is null")
                }
            }

            override fun onFailure(call: Call<List<Array<String>>>, t: Throwable) {
                Log.e("MainActivity", "Failed to make API call: ${t.message}")
            }
        })
    }


}

interface TransactionHistoryApi {
    @GET("/transactionHistory")
    fun getTransactionHistory(): Call<List<Array<String>>>
}