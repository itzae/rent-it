package com.itgonca.rentit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.itgonca.rentit.data.remote.model.Location
import com.itgonca.rentit.domain.repository.FirebaseDatabaseRepository
import com.itgonca.rentit.utils.functional.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val databaseRepository: FirebaseDatabaseRepository,
    private val auth: FirebaseAuth
) :
    ViewModel() {

    private var _listLocations = MutableStateFlow<List<Location>>(emptyList())
    val listLocations: StateFlow<List<Location>> get() = _listLocations

    init {
        getListLocations()
    }

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

    fun updateFavorite( idLocation: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            val result = databaseRepository.updateFavoriteLocation(auth.currentUser?.uid ?: "", idLocation, isFavorite)
            result.eitther(::errorUpdate, ::completeUpdate)
        }
    }

    private fun completeUpdate(void: Void?) {

    }

    private fun errorUpdate(failure: Failure) {

    }

    private fun successList(data: DataSnapshot, listResult: MutableList<Location>): List<Location> {
        listResult.clear()
        data.children.forEach { children ->
            listResult.add((children.getValue(Location::class.java)!!).apply {
                id = children.key?.toInt() ?: 0
            })
        }
        return listResult
    }

    fun getFilters() = listOf("3 Guests", "Apartment", "WIFI", "Restaurant", "A/C")
}