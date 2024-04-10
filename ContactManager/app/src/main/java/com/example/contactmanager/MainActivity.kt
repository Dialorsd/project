package com.example.app

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.EdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var buttonRL: Button
    private lateinit var button_login: Button
    private lateinit var phoneL: EditText
    private lateinit var passwordL: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EdgeToEdge.enable(this)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        buttonRL = findViewById<Button>(R.id.buttonRL) // Assuming buttonRL is the register button
        button_login = findViewById<Button>(R.id.button_login)
        phoneL = findViewById<EditText>(R.id.phoneL)
        passwordL = findViewById<EditText>(R.id.passwordL) // Assuming passwordL is the password field

        button_login.setOnClickListener {
            val phone = phoneL.text.toString()
            val password = passwordL.text.toString()

            if (isValidLogin(phone) && isValidPassword(password)) {
                val savedPhone = sharedPreferences.getString("phone", "")
                val savedPassword = sharedPreferences.getString("password", "")

                if (phone == savedPhone && password == savedPassword) {
                    // Login successful
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainPage::class.java)
                    startActivity(intent)
                    // Intent intent = Intent(this, LoggedInActivity::class.java)
                    // startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid phone number or password!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid phone number or password!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidLogin(login: String): Boolean {
        // Check length
        if (login.length != 10) {
            return false
        }
        // Check for digits
        for (c in login.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false
            }
        }
        // Valid login
        return true
    }

    private fun isValidPassword(password: String): Boolean {
        val PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{8,24}")
        return !TextUtils.isEmpty(password) && PASSWORD_PATTERN.matcher(password).matches()
    }

    fun RegestrationMove(v: View) {
        val intent = Intent(this, Regestration::class.java)
        startActivity(intent)
    }
}
