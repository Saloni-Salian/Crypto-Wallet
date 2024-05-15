package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.gson.GsonBuilder
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ViewSessions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_sessions)

        val goBack = findViewById<Button>(R.id.go_back3)

        goBack.setOnClickListener {
            val intent = Intent(this, Paid::class.java)
            startActivity(intent)
        }

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            //change your ip address to your local computer's ip address before running
            .baseUrl("http://192.168.1.114:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(BookingsApi::class.java)
        val test2 = findViewById<TextView>(R.id.textView4)

        api.getBookedSessions().enqueue(object : Callback<List<Array<String>>> {
            override fun onResponse(call: Call<List<Array<String>>>, response: Response<List<Array<String>>>) {
                val BookedSessions = response.body()

                if (BookedSessions != null) {
                    for (sessions in BookedSessions) {
                          test2.append("Session id: " +sessions[0]+ "\n" + "Consultant Id: "+ sessions[1]+  "\n" + "Consultant Name: "+ sessions[2]+ "\n" + "Customer ID: "+ sessions[3] + "\n" + "Booking Timestamp: " +sessions[4] + "\n" + "Session Date Time: " +sessions[5] +"\n" + "Booking URL: "+sessions[6] + "\n" +"------------------------------------------------------------------------------------------   " +"\n" )

                        test2.movementMethod = ScrollingMovementMethod()

//



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








interface BookingsApi {
    @GET("/viewBookedSessions")
    fun getBookedSessions(): Call<List<Array<String>>>
}