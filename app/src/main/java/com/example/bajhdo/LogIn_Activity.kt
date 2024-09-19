package com.example.bajhdo
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LogIn_Activity : AppCompatActivity() {
    private lateinit var btnLogIn: Button
    private lateinit var txtSignIn: TextView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)


        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        setContentView(R.layout.activity_log_in)
        txtSignIn = findViewById(R.id.txtSignup)
        btnLogIn = findViewById(R.id.btnLogIn)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)

        txtSignIn.setOnClickListener {
            val intent = Intent(this@LogIn_Activity, SignIn_Activity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogIn.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            logIn(email, password)
        }
    }

    private fun logIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LogIn_Activity, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show()
                }
            }
    }
}