package com.example.anchorbay.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BayViewModel: ViewModel() {

    private val _bays = MutableLiveData<List<Bay>>(listOf())
    val bays: LiveData<List<Bay>> = _bays

    fun addNewBay(bay: Bay) {
        _bays.value = _bays.value?.plus(bay)
    }

    fun getBay(bayId: String): Bay? {
        return bays.value?.find { it.nickname == bayId }
    }

    fun updateBay(bay: Bay) {

    }
}