package com.example.myfavoritecat.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.Entity.CatEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ObserveCatViewModel @Inject constructor() : ViewModel() {
    val selectedCat: MutableState<CatEntity?> = mutableStateOf(null)

    fun updateCat(cat: CatEntity) {
        selectedCat.value = cat
    }
}