package com.example.mobileapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button;
import android.widget.TextView
import android.widget.Toast
import com.example.mobileapplication.R
import com.example.mobileapplication.api.ApiClient
import com.example.mobileapplication.utils.SessionManager
import com.example.mobileapplication.api.requests.LoginRequest
import com.example.mobileapplication.api.responses.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(){
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginUser(view: View) {
        val textViewUsername: TextView = findViewById(R.id.username) as TextView
        val textViewPassword: TextView = findViewById(R.id.mypass) as TextView

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        apiClient.getApiService(this).login(
            LoginRequest(
                username = textViewUsername.text.toString(),
                textViewPassword.text.toString()
            )
        ).enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Error logging in
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    println(response.body()?.token)

                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.token != null) {
                        sessionManager.saveAuthToken(loginResponse.token)

                        val intent = Intent(applicationContext, MenuActivity::class.java)
                        startActivity(intent);
                        this@LoginActivity.finish()
                    } else {
                        Toast.makeText(applicationContext, "error!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }
}
