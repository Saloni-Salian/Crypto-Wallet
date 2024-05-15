package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val cusloginButton = findViewById<Button>(R.id.Customer)
        cusloginButton.setOnClickListener {
            val intent = Intent(this, CustomerLogin::class.java)
            startActivity(intent)

        }
        val staffloginButton = findViewById<Button>(R.id.Staff)
        staffloginButton.setOnClickListener {
            val intent = Intent(this, StaffLogin::class.java)
            startActivity(intent)

        }

    }




}