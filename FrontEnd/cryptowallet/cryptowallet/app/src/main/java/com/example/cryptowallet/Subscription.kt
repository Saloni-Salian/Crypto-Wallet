package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Subscription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription)


        val upgradeButton = findViewById<Button>(R.id.upgradeButton)
        upgradeButton.setOnClickListener {
            val intent = Intent(this, Upgrade::class.java)
            startActivity(intent)
        }

        val paidButton = findViewById<Button>(R.id.paidFeaturesButton)
        paidButton.setOnClickListener {
            val intent = Intent(this, Paid::class.java)
            startActivity(intent)
        }

        val downgradeButton = findViewById<Button>(R.id.downgradeButton)
        downgradeButton.setOnClickListener {
            val intent = Intent(this, Downgrade::class.java)
            startActivity(intent)
        }

        val backButton = findViewById<Button>(R.id.backButton3)
        backButton.setOnClickListener {
            val intent = Intent(this, MainAccount::class.java)
            startActivity(intent)
        }
    }
}