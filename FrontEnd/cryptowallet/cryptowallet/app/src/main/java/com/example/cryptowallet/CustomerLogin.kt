package com.example.cryptowallet

import android.content.Intent
import android.widget.EditText
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.gson.GsonBuilder
import retrofit2.http.Body
import retrofit2.http.POST



class CustomerLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customerlogin)
        val loginfinal = findViewById<Button>(R.id.button2)
        val userinput = findViewById<EditText>(R.id.editTextNumberPassword2)
        val context = applicationContext



        loginfinal.setOnClickListener {
//             Retrieve the PIN value from the input field
            val pin = userinput.text.toString()
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                //change your ip address to your local computer's ip address before running
                .baseUrl("http://192.168.1.114:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()




            // Create an instance of the API interface using the Retrofit instance
            val api = retrofit.create(MyAPI::class.java)
            val pass = pin
            fun loggedin() {
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
            }
            api.postPass(pass).enqueue(object : Callback<String> {

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val pass = response.body()
                    if (pass != null && pass =="true")
                    {

                        Toast.makeText(context, "Successfully Logged in", Toast.LENGTH_LONG).show()
                        loggedin()


                    }
                    else if (pass != null && pass =="false"){
                        // Display an error message using a Toast
                        Toast.makeText(context, "Wrong Pin, Please re enter the pin", Toast.LENGTH_LONG).show()
                        userinput.setText("")

                    }
                    else {
                        Log.d("MainActivity", "Response body is null")
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("MainA ctivity", "Failed to make API call: ${t.message}")
                }
            })


//            val intent = Intent(this, HomePageActivity::class.java)
//            startActivity(intent)


        }
    }

    // Define an API interface that specifies the POST endpoint
    interface MyAPI {
        @POST("customerPin/")
        fun postPass(@Body pass: String): Call<String>
    }


}