package com.samra.mvvmtodolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.samra.mvvmtodolist.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       gotoDashBoard()
    }
  //splash screen go to signup
    private fun gotoDashBoard() {
        Handler().postDelayed({
            val mIntent = Intent(this@SplashActivity, SignUpActivity::class.java)
            startActivity(mIntent)
            finish()
        }, 2000)
    }

}