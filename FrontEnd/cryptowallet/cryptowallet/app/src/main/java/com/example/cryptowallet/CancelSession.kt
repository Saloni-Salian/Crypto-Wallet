package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



class CancelSession : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel_session)

        val goBack = findViewById<Button>(R.id.go_back3)
        val deletebutton = findViewById<Button>(R.id.submit3)
        val inputtediddelete = findViewById<EditText>(R.id.deleteidinput)
        val context = applicationContext
        goBack.setOnClickListener {
            val intent = Intent(this, Paid::class.java)
            startActivity(intent)
        }

        deletebutton.setOnClickListener {
            val deleteid = Integer.parseInt(inputtediddelete.text.toString())
            val gson = GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                //change your ip address to your local computer's ip address before running
                .baseUrl("http://192.168.1.114:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            val api = retrofit.create(CancelBookingApi::class.java)



            api.cancelBooking(deleteid).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val cancelBookingResponse = response.body()
                    if (cancelBookingResponse != null && cancelBookingResponse.toBoolean()) {
                        Toast.makeText(context, "Booking cancelled successfully", Toast.LENGTH_LONG).show()
                    } else {

                        Toast.makeText(context, "Failed to cancel booking", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("MainActivity", "Failed to make API call: ${t.message}")
                }
            })


        }


        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.114:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(BookingsApi::class.java)
        val test2 = findViewById<TextView>(R.id.textView25)

        api.getBookedSessions().enqueue(object : Callback<List<Array<String>>> {
            override fun onResponse(
                call: Call<List<Array<String>>>,
                response: Response<List<Array<String>>>
            ) {
                val BookedSessions = response.body()

                if (BookedSessions != null) {
                    for (sessions in BookedSessions) {
                        test2.append("Session id: " + sessions[0] + "\n" + "Consultant Id: " + sessions[1] + "\n" + "Consultant Name: " + sessions[2] + "\n" + "Customer ID: " + sessions[3] + "\n" + "Booking Timestamp: " + sessions[4] + "\n" + "Session Date Time: " + sessions[5] + "\n" + "Booking URL: " + sessions[6] + "\n" + "------------------------------------------------------------------------------------------   " + "\n")

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


    interface CancelBookingApi {
        @POST("/cancelBooking/")
        fun cancelBooking(@Body sessionID: Int): Call<String>
    }

}



