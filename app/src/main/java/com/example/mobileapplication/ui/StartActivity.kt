package com.example.mobileapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileapplication.R
import com.example.mobileapplication.utils.SessionManager

class StartActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        val intent: Intent
        if(sessionManager.fetchAuthToken() == null){
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent);
            finish()
        }else{
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent);
            finish()
        }
        }
}