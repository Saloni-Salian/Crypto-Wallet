package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



class BookSessions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_sessions)
        val sendinfo = findViewById<Button>(R.id.submit2)
        val inputconsultancyid = findViewById<EditText>(R.id.consultancyidinput)
        val inputconsultancyname = findViewById<EditText>(R.id.consultancynameinput)
        val inputdate = findViewById<EditText>(R.id.dateinput)



        sendinfo.setOnClickListener {
            val gson = GsonBuilder().setLenient().create()
            val inputtedconsultid = inputconsultancyid.text.toString()
            val inputedconsultancyname = inputconsultancyname.text.toString()
            val inputeddate = inputdate.text.toString()
            val context = applicationContext



        val retrofit = Retrofit.Builder()
            //change your ip address to your local computer's ip address before running
            .baseUrl("http://192.168.1.114:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api = retrofit.create(CreateBookingApi::class.java)


        val sessionDetails = arrayOf(inputtedconsultid, inputedconsultancyname, inputeddate)
        api.createBooking(sessionDetails).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                Log.d("printing name",inputedconsultancyname)
                Log.d("test",inputtedconsultid)
                Log.d("date ", inputeddate)
                val bookingResponse = response.body()
                if (bookingResponse != null && bookingResponse.toBoolean()) {
                    Log.d("MainActivity", "Booking created successfully")
                    Toast.makeText(context, "Booking created successfully", Toast.LENGTH_LONG).show()

                } else {
                    Log.d("MainActivity", "Failed to create booking")
                    Toast.makeText(context, "Failed to create booking", Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("MainActivity", "Failed to make API call: ${t.message}")
            }
        })
//


        }
        val goBack = findViewById<Button>(R.id.go_back3)

        goBack.setOnClickListener {
            val intent = Intent(this, Paid::class.java)
            startActivity(intent)
        }
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.114:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val displaytwo = findViewById<TextView>(R.id.textView24)
        val api = retrofit.create(AvaliableBookingsApi::class.java)

        api.getAvailableSessions().enqueue(object : Callback<List<Array<String>>> {
            override fun onResponse(call: Call<List<Array<String>>>, response: Response<List<Array<String>>>) {
                val AvailableSessions = response.body()

                if (AvailableSessions != null) {
                    for (sessions in AvailableSessions) {

                        displaytwo.append("Consultant id: " +sessions[0]+ "\n" + "Consultant Name: "+ sessions[1]+  "\n" + "Date:  "+ sessions[2]+ "\n" +"------------------------------------------------------------------------------------------   " +"\n" )

                        displaytwo.movementMethod = ScrollingMovementMethod()

                        Log.d("MainActivity", "Consultant ID: ${sessions[0]}")
                        Log.d("MainActivity", "Consultant Name: ${sessions[1]}")
                        Log.d("MainActivity", "Date: ${sessions[2]}")
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

interface AvaliableBookingsApi {
    @GET("/viewAvailableSessions")
    fun getAvailableSessions(): Call<List<Array<String>>>
}
interface CreateBookingApi {
    @POST("/createBooking/")
    fun createBooking(@Body sessionDetails: Array<String>): Call<String>
}










