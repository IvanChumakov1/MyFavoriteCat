package com.example.myfavoritecat.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Entity.CatEntity
import com.example.domain.UseCase.DeleteCatDBUseCase
import com.example.domain.UseCase.GetCatsDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCatsViewModel @Inject constructor(
    private val getCatsDBUseCase: GetCatsDBUseCase,
    private val deleteCatDBUseCase: DeleteCatDBUseCase
) : ViewModel() {
    val cats = getCatsDBUseCase.invoke()

    fun removeCat(cat: CatEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCatDBUseCase.invoke(cat)
        }
    }
}