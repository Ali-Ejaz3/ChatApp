package com.example.bajhdo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn_Activity : AppCompatActivity() {
    private lateinit var btnSignIn: Button
    private lateinit var txtLogIn: TextView
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtName: EditText
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()


        btnSignIn = findViewById(R.id.btnSignIn)
        txtLogIn = findViewById(R.id.txtLogIn)
        edtEmail = findViewById(R.id.edtEmail)
        edtName = findViewById(R.id.edtName)
        edtPassword = findViewById(R.id.edtPassword)
        mAuth = FirebaseAuth.getInstance()

        btnSignIn.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val name = edtName.text.toString()


            signIn(name, email, password)
        }
        txtLogIn.setOnClickListener {
            val intent = Intent(this@SignIn_Activity,LogIn_Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun signIn(name:String,email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@SignIn_Activity,LogIn_Activity::class.java)
                    finish()
                    startActivity(intent)
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)

                } else {
                    Toast.makeText(this,"Some Error Occured", Toast.LENGTH_LONG).show()

                }
            }

    }
    private fun addUserToDatabase(name:String, email: String, uid:String){
    mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))

    }
}