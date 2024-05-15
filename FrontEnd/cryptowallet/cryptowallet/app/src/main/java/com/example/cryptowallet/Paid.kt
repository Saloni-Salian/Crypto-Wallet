package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Paid : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paid)

        val backButton = findViewById<Button>(R.id.backButton6)
        backButton.setOnClickListener {
            val intent = Intent(this, Subscription::class.java)
            startActivity(intent)
        }

        val requestButton = findViewById<Button>(R.id.requestConsulButton)
        requestButton.setOnClickListener {
            val intent = Intent(this, BookSessions::class.java)
            startActivity(intent)
        }

        val cancelButton = findViewById<Button>(R.id.cancelConsulButton)
        cancelButton.setOnClickListener {
            val intent = Intent(this, CancelSession::class.java)
            startActivity(intent)
        }

        val viewConsul = findViewById<Button>(R.id.viewconsulbutton)
        viewConsul.setOnClickListener {
            val intent = Intent(this, ViewSessions::class.java)
            startActivity(intent)
        }
    }
}