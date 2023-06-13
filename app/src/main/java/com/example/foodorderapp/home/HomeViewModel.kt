package com.example.foodorderapp.home

import androidx.lifecycle.viewModelScope
import com.example.domain.delegate.CurrentCityDelegate
import com.example.domain.entity.CategoryUiEntity
import com.example.domain.usecase.GetCategoryUseCase
import com.example.foodorderapp.core.base.BaseViewModel
import com.example.foodorderapp.core.navigation.Command
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryUseCase: GetCategoryUseCase,
    val currentCityDelegate: CurrentCityDelegate,
) : BaseViewModel() {

    private val _categoriesList = MutableStateFlow<List<CategoryUiEntity?>?>(null)
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

    fun navigateToDishFragment(title: String) {
        val dir = HomeFragmentDirections.toDishesFragment(title)
        sendCommand(Command.NavCommand(dir))
    }

}