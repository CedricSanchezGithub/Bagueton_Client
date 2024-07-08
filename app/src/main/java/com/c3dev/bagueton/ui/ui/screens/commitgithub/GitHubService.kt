package com.c3dev.bagueton.ui.ui.screens.commitgithub

import com.c3dev.bagueton.ui.model.beans.Commit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Interface définissant le service GitHub
interface GitHubService {
    @GET("repos/{owner}/{repo}/commits")
    fun listCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<List<Commit>>
}

// Objet singleton pour initialiser et configurer Retrofit
object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    // Intercepteur pour le logging des requêtes HTTP
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Token GitHub à utiliser pour l'authentification
    private const val YOUR_GITHUB_TOKEN = ""

    // Client HTTP configuré avec les intercepteurs
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "token $YOUR_GITHUB_TOKEN")
                .build()
            chain.proceed(request)
        }
        .build()

    // Instance Retrofit configurée
    val instance: GitHubService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(GitHubService::class.java)
    }
}
