package com.example.myfavoritecat.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Entity.CatEntity
import com.example.domain.UseCase.DeleteCatDBUseCase
import com.example.domain.UseCase.GetCatsDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCatsViewModel @Inject constructor(
    private val getCatsDBUseCase: GetCatsDBUseCase,
    private val deleteCatDBUseCase: DeleteCatDBUseCase
) : ViewModel() {
    private val _cats = MutableStateFlow<List<CatEntity>>(emptyList())
    val cats: StateFlow<List<CatEntity>> = _cats.asStateFlow()

    init {
        viewModelScope.launch {
            getCatsDBUseCase.invoke().collect { catList ->
                _cats.value = catList
            }
        }
    }

    fun removeCat(cat: CatEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCatDBUseCase.invoke(cat)
        }
    }

    fun sortByAlphabetAZ() {
        _cats.value = _cats.value.sortedBy { it.name }
    }

    fun sortByAlphabetZA() {
        _cats.value = _cats.value.sortedByDescending { it.name }
    }
}