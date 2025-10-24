package com.unsoed.norwichcityfc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsoed.norwichcityfc.data.TeamResponse
import com.unsoed.norwichcityfc.network.RetrofitClient
import kotlinx.coroutines.launch

class ClubViewModel : ViewModel() {

    // LiveData untuk menampung data tim
    private val _teamData = MutableLiveData<TeamResponse?>()
    val teamData: LiveData<TeamResponse?> = _teamData

    // LiveData untuk status loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData untuk pesan error
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // Fungsi untuk mengambil data dari API
    fun fetchTeamData(teamId: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // Panggil API
                val response = RetrofitClient.instance.getTeamDetails(teamId)
                _teamData.postValue(response)
                _isLoading.postValue(false)
            } catch (e: Exception) {
                _error.postValue("Gagal mengambil data: ${e.message}")
                _isLoading.postValue(false)
            }
        }
    }
}