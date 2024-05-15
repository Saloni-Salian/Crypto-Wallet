package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class registerOptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_options)

        val createbutton = findViewById<Button>(R.id.create)
        createbutton.setOnClickListener {
            Toast.makeText(this@registerOptions,"Coming soon", Toast.LENGTH_SHORT).show()
        }

        val importbutton = findViewById<Button>(R.id.importwallet)
        importbutton.setOnClickListener {
            Toast.makeText(this@registerOptions,"Coming soon", Toast.LENGTH_SHORT).show()
        }

        val backButton = findViewById<Button>(R.id.bbutton)
        backButton.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)

        }
    }
}