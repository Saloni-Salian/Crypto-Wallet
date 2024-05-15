package com.example.cryptowallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class StaffLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_login)

        val loginButton = findViewById<Button>(R.id.button4)
        loginButton.setOnClickListener {
            Toast.makeText(this,"Coming soon", Toast.LENGTH_SHORT).show()
        }
    }
}