package com.itgonca.rentit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.getValue
import com.itgonca.rentit.data.remote.model.Location
import com.itgonca.rentit.domain.repository.FirebaseDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val databaseRepository: FirebaseDatabaseRepository) :
    ViewModel() {

    private var _listLocations = MutableLiveData<List<Location>>()
    val listLocations: LiveData<List<Location>> get() = _listLocations

    fun getListLocations() {
        val listResult = mutableListOf<Location>()
        viewModelScope.launch {
            databaseRepository.getListLocations().map {
                successList(it, listResult)
            }.catch { e ->
                Log.d("TAG", "getListLocations: $e")
            }.collect {
                _listLocations.value = it
            }

        }
    }

    private fun successList(data: DataSnapshot, listResult: MutableList<Location>): List<Location> {
        listResult.clear()
        data.children.forEach { children ->
            listResult.add((children.getValue<Location>() as Location).apply {
                id = children.key?.toInt() ?: 0
            })
        }
        return listResult
    }
}