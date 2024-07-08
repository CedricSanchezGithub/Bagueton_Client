package com.c3dev.bagueton.ui.ui.screens.commitgithub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.c3dev.bagueton.ui.model.beans.Commit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Commit(
    val sha: String,
    val commit: CommitDetails
)

data class CommitDetails(
    val message: String,
    val author: Author
)

data class Author(
    val name: String,
    val date: String
)


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
