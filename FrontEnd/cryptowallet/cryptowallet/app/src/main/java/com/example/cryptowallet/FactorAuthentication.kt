package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class FactorAuthentication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factor_authentication)

        val continueButton = findViewById<Button>(R.id.Enter)
        continueButton.setOnClickListener {
            Toast.makeText(this,"Coming soon", Toast.LENGTH_SHORT).show()
        }

        val backButton = findViewById<Button>(R.id.backButton5)
        backButton.setOnClickListener {
            val intent = Intent(this, MainAccount::class.java)
            startActivity(intent)
        }

    }
}