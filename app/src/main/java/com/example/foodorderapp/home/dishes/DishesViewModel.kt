package com.example.foodorderapp.home.dishes

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.DishUiEntity
import com.example.domain.usecase.GetDishesUseCase
import com.example.foodorderapp.core.base.BaseViewModel
import com.example.foodorderapp.core.navigation.Command
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DishesViewModel @Inject constructor(
    private val getDishesUseCase: GetDishesUseCase,
) : BaseViewModel() {

    private val _dishesList = MutableStateFlow<List<DishUiEntity?>?>(null)
    val dishesList = _dishesList.asStateFlow()

    private val _tagsList = MutableStateFlow<Set<String>?>(null)
    val tagsList = _tagsList.asStateFlow()

    private var _allDishes = listOf<DishUiEntity?>()

    init {
        getDishesList()
    }

    fun getDishesList() {
        getDishesUseCase.invoke()
            .onEach { dishes ->
                _tagsList.value =
                    dishes.flatMap { it?.tags ?: listOf() }.toSet()
                _allDishes = dishes
                _dishesList.value = dishes
            }
            .catch {
                it.printStackTrace()
            }.launchIn(viewModelScope)
    }

    fun updateDishesAccordingTag(tagTitle: String) {
        _dishesList.update {
            _allDishes.filter { it?.tags?.contains(tagTitle) ?: false }
        }
    }

    fun navigateToDishDetails(dish: DishUiEntity) {
        val dir = DishesFragmentDirections.toDishDetailsDialogFragment(dish)
        sendCommand(Command.NavCommand(dir))
    }

}