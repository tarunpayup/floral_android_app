package com.im_android_developer.floralecommerce

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private fun validatePassword(password: String): String? {
        val digits = password.count { it.isDigit() }
        val lowers = password.count { it.isLowerCase() }
        val uppers = password.count { it.isUpperCase() }
        val symbols = password.count { !it.isLetterOrDigit() }

        val missing = mutableListOf<String>()
        if (digits < 8) missing.add("at least 8 digits")
        if (lowers < 8) missing.add("at least 8 lowercase letters")
        if (uppers < 1) missing.add("at least 1 uppercase letter")
        if (symbols < 1) missing.add("at least 1 symbol")

        return if (missing.isEmpty()) null
        else "Password must contain ${missing.joinToString(", ")}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val loginId: TextInputLayout = findViewById<TextInputLayout>(R.id.user_id)
        val loginPwd: TextInputLayout = findViewById<TextInputLayout>(R.id.user_password)
        val loginButton: Button = findViewById<Button>(R.id.login_btn)

        loginButton.setOnClickListener {
            val loginValue: String = loginId.editText?.text?.toString()?.trim().orEmpty()
            val loginPassword: String = loginPwd.editText?.text?.toString()?.trim().orEmpty()

            loginPwd.error = null
            val error = validatePassword(loginPassword)
            if (error == null) {
                Toast.makeText(this, "Password is valid", Toast.LENGTH_LONG).show()
            } else {
                loginPwd.error = error
            }
        }
    }
}