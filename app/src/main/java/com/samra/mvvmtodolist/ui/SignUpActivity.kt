package com.samra.mvvmtodolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast

import com.samra.mvvmtodolist.R
import com.samra.mvvmtodolist.databinding.ActivitySignupBinding
import com.samra.mvvmtodolist.util.UserInfoPreference
import kotlinx.android.synthetic.main.activity_signup.*


class SignUpActivity : AppCompatActivity() {
//use viewbinding
    lateinit var binding: ActivitySignupBinding
    val userInfoPreference = UserInfoPreference(this@SignUpActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        binding.loginlink.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    // Checking if the input in form is valid
    fun validateInput(): Boolean {
        if (binding.nameTv.text.toString().equals("")) {
            binding.nameTv.setError("Please Enter Name")
            return false
        }

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

    // signup click check validation and save data in shared preferences

    fun performSignUp (view: View) {
        if (validateInput()) {

            // Input is valid, here send data to your server also

            val Name = binding.nameTv.text.toString()
            val email = binding.numberTv.text.toString()
            val password = binding.passwordTv.text.toString()

            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("name",Name)
            editor.putString("email",email)
            editor.putString("password",password)
            editor.commit()

            Toast.makeText(this,"SignUp Successful",Toast.LENGTH_SHORT).show()

            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
            startActivity(intent)


        }
    }


}