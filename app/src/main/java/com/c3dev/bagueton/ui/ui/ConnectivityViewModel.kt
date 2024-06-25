package com.c3dev.bagueton.ui.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ConnectivityViewModel(application: Application) : AndroidViewModel(application) {
    val networkStatus: LiveData<Boolean> = NetworkStatusLiveData(application)
}
