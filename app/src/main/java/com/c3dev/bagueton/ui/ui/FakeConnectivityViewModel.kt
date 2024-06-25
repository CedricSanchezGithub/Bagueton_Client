package com.c3dev.bagueton.ui.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FakeConnectivityViewModel : ViewModel() {
    private val _networkStatus = MutableLiveData(true)
    val networkStatus: LiveData<Boolean> = _networkStatus
}