package com.example.wear

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignInActivity : WearableActivity() {
    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var button: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
        button = findViewById(R.id.signIn)

        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)

        val savedLogin = sharedPreferences.getString("login", "")
        val savedPassword = sharedPreferences.getString("password", "")

        login.setText(savedLogin)
        password.setText(savedPassword)

        button.setOnClickListener {
            val enteredLogin = login.text.toString()
            val enteredPassword = password.text.toString()

            if (enteredPassword.length >= 8) {
                val editor = sharedPreferences.edit()
                editor.putString("login", enteredLogin)
                editor.putString("password", enteredPassword)
                editor.apply()

                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Пароль должен быть не менее 8 символов", Toast.LENGTH_LONG).show()
            }
        }
    }
}


