package com.c3dev.bagueton.ui.ui.screens.tests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GitHubService {
    @Headers("Authorization: token YOUR_PERSONAL_ACCESS_TOKEN")
    @GET("repos/{owner}/{repo}/commits")
    fun listCommits(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<List<Commit>>
}

data class Commit(
    val sha: String,
    val commit: CommitDetails
)

data class CommitDetails(
    val message: String,
    val author: CommitAuthor
)

data class CommitAuthor(
    val name: String,
    val date: String
)


object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val instance: GitHubService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(GitHubService::class.java)
    }
}


class CommitsViewModel : ViewModel() {
    private val _commits = MutableLiveData<List<Commit>>()
    val commits: LiveData<List<Commit>> = _commits

    fun fetchCommits(owner: String, repo: String) {
        RetrofitClient.instance.listCommits(owner, repo).enqueue(object : Callback<List<Commit>> {
            override fun onResponse(call: Call<List<Commit>>, response: Response<List<Commit>>) {
                if (response.isSuccessful) {
                    _commits.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Commit>>, t: Throwable) {
                // Gérer l'erreur
            }
        })
    }
}
