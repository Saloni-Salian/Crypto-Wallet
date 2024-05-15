package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Upgrade : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade)


//      when button is pressed alert appears
        val yesButton = findViewById<Button>(R.id.yesButton)
        yesButton.setOnClickListener {
            Toast.makeText(this,"Coming soon", Toast.LENGTH_SHORT).show()
        }

//      when button is pressed alert appears
        val noButton = findViewById<Button>(R.id.noButton)
        noButton.setOnClickListener {
            Toast.makeText(this,"Account not upgraded", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }

        val backButton = findViewById<Button>(R.id.backButton7)
        backButton.setOnClickListener {
            val intent = Intent(this, Subscription::class.java)
            startActivity(intent)
        }
    }
}