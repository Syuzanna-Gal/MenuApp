package com.example.foodorderapp.home

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CategoryUiEntity
import com.example.domain.usecase.GetCategoryUseCase
import com.example.foodorderapp.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
) : BaseViewModel() {

    private val _categoriesList = MutableStateFlow<List<CategoryUiEntity>?>(null)
    val categoriesList = _categoriesList.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoryUseCase()
            .onEach {
                _categoriesList.value = it
            }
            .catch {
                it.printStackTrace()
            }
            .launchIn(viewModelScope)
    }
}