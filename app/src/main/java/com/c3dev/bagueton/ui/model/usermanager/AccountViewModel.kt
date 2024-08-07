package com.c3dev.bagueton.ui.model.usermanager

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.c3dev.bagueton.ui.model.beans.ApiResponse
import com.c3dev.bagueton.ui.model.beans.LoginRequest
import com.c3dev.bagueton.ui.model.beans.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel : ViewModel(){

    val choosenUsername = mutableStateOf("")
    val choosenPassword = mutableStateOf("")
    val choosenPasswordAgain = mutableStateOf("")

    fun registerUser(user: User, onResult: (String) -> Unit) {
        RetrofitClient.instance.registerUser(user).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    onResult(response.body()?.message ?: "Success")
                } else {
                    onResult("Registration failed")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(t.message ?: "Unknown error")
            }
        })
    }

    fun loginUser(username: String, password: String, onResult: (String) -> Unit) {
        val loginRequest = LoginRequest(username, password)
        RetrofitClient.instance.loginUser(loginRequest).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    SessionManager.token = apiResponse?.token
                    onResult("Login successful")
                } else {
                    onResult("Login failed")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(t.message ?: "Unknown error")
            }
        })
    }

}
