package com.example.myfavoritecat.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Entity.CatEntity
import com.example.domain.UseCase.AddCatDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCatsViewModel @Inject constructor(
    private val addCatDBUseCase: AddCatDBUseCase
) : ViewModel() {
    fun addCat(cat: CatEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addCatDBUseCase.invoke(cat)
        }
    }
}