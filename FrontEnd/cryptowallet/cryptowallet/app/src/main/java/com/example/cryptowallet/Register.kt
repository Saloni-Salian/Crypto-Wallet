package com.example.cryptowallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.view.View.OnFocusChangeListener



class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val submitButton = findViewById<Button>(R.id.Sbutton)
        submitButton.setOnClickListener {
            val intent = Intent(this,registerOptions::class.java)
            startActivity(intent)

        }

        val backButton2 = findViewById<Button>(R.id.b2button)
        backButton2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        fun validatePassword(password: String): Boolean {
            val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{8,30}\$".toRegex()
            return password.matches(pattern)
        }


        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        passwordEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val password = passwordEditText.text.toString()
                if (!validatePassword(password)) {
                    passwordEditText.error = ": 8-30\n" +
                            "characters, at least 1 upper case and 1 lower case letter, at least 1 special\n" +
                            "character, at least 1 number.\n"
                }
            }
        }


    }


}