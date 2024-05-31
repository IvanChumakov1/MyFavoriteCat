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

@HiltViewModel
class ObserveSearchCatsViewModel @Inject constructor(
    private val detCatsAPIUseCase: GetCatsAPIUseCase
) : ViewModel() {

    // Ломается тут
    var searchedCats: MutableState<List<CatEntity>?> = mutableStateOf(listOf())
        private set

    fun searchCats(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Hello", detCatsAPIUseCase.invoke(name).toString())
            val found = detCatsAPIUseCase.invoke(name)
            searchedCats.value = found
        }
    }
}