package com.example.myfavoritecat.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Entity.CatEntity
import com.example.domain.UseCase.GetCatsAPIUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State
import java.util.UUID

@HiltViewModel
class ObserveSearchCatsViewModel @Inject constructor(
    private val detCatsAPIUseCase: GetCatsAPIUseCase
) : ViewModel() {

    private val _searchedCats: MutableState<List<CatEntity>?> = mutableStateOf(listOf())
    val searchedCats: State<List<CatEntity>?> = _searchedCats
    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    private val _selectedCat: MutableState<CatEntity?> = mutableStateOf(null)
    val selectedCat: State<CatEntity?> = _selectedCat
    private val cats: MutableState<MutableList<CatEntity>> = mutableStateOf(mutableListOf())
    fun searchCats(name: String) {
        _searchQuery.value = name
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Hello", detCatsAPIUseCase.invoke(name).toString())
            val found = detCatsAPIUseCase.invoke(name)
            _searchedCats.value = found
        }
    }
    fun updateCat(cat: CatEntity) {
        _selectedCat.value = cat
    }

    fun addCat(cat: CatEntity): String {
        val catId = UUID.randomUUID().toString()
        val newCat = cat.copy(id = catId) // Копируем кота с новым id
        cats.value.add(newCat)
        return catId
    }

    fun getCatById(catId: String?): CatEntity? {
        return cats.value.find { it.id == catId }
    }
}