package com.c3dev.bagueton.ui.model.usermanager



import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/auth/register")
    fun registerUser(@Body user: User): Call<ApiResponse>

    @POST("/api/auth/login")
    fun loginUser(@Body request: LoginRequest): Call<ApiResponse>
}
