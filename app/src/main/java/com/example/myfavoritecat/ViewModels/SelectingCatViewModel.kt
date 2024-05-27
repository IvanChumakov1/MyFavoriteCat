package com.example.myfavoritecat.ViewModels

import androidx.lifecycle.ViewModel
import com.example.domain.Entity.CatEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SelectingCatViewModel @Inject constructor() : ViewModel() {
    private val _selectedCat = MutableStateFlow<CatEntity?>(null)

    val selectedCat: StateFlow<CatEntity?> = _selectedCat.asStateFlow()

    fun selectCat(cat: CatEntity) {
        _selectedCat.value = cat
    }

    fun clear() {
        _selectedCat.value = null
    }
}