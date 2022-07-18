package com.samra.mvvmtodolist.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast


import com.samra.mvvmtodolist.R
import com.samra.mvvmtodolist.util.UserInfoPreference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import com.samra.mvvmtodolist.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
//use view binding
    lateinit var binding:  ActivityLoginBinding
    val userInfoPreference = UserInfoPreference(this@LoginActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        binding.signuplink.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    // Checking if the input in form is valid
    fun validateInput(): Boolean {

        if (binding.numberTv.text.toString().equals("")) {
            binding.numberTv.setError("Please Enter Email")
            return false
        }
        if (binding.passwordTv.text.toString().equals("")) {
            binding.passwordTv.setError("Please Enter Password")
            return false
        }

        // checking the proper email format
        if (!isEmailValid(binding.numberTv.text.toString())) {
            binding.numberTv.setError("Please Enter Valid Email")
            return false
        }
        return true
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    //Login click check validation then check if data saved in shared preference than login

    fun performLogin (view: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server


            val email = binding.numberTv.text.toString()
            val password = binding.passwordTv.text.toString()
            val prefs = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

            if (email == prefs.getString("username","") ||password == prefs.getString("password","") ) {
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Email Password Incorrect",Toast.LENGTH_SHORT).show()

            }
        }
    }


}