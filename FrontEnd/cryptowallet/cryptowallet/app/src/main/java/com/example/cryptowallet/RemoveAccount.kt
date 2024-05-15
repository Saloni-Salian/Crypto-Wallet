package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class RemoveAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_account)

//        need to change so when button is pressed alert appears
        val yesButton = findViewById<Button>(R.id.yesButton2)
        yesButton.setOnClickListener {
            Toast.makeText(this,"Coming soon", Toast.LENGTH_SHORT).show()
        }

//        need to change so when button is pressed alert appears
        val noButton = findViewById<Button>(R.id.noButton2)
        noButton.setOnClickListener {
            Toast.makeText(this,"Account not removed",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }

        val backButton = findViewById<Button>(R.id.backButton8)
        backButton.setOnClickListener {
            val intent = Intent(this, MainAccount::class.java)
            startActivity(intent)
        }
    }
}