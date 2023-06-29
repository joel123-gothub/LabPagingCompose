package com.example.labdanp.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.labdanp.dataRepository.DataRepository

class DataViewModelFactory(private val dataRepository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataViewModel::class.java)) {
            return DataViewModel(dataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}