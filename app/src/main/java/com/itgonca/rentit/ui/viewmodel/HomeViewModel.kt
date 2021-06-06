package com.itgonca.rentit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.itgonca.rentit.domain.repository.FirebaseDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val databaseRepository: FirebaseDatabaseRepository) :
    ViewModel() {

        fun getListLocations() {
            viewModelScope.launch {
                databaseRepository.getListLocations().eitther(::Errors,::Successd)

        }

    private fun Successd(data: Flow<DataSnapshot>){
        data.collect {
            Log.d("TAG", "Successd: $it")
        }
    }
}