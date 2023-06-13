package com.example.foodorderapp.home.dishes.dishDetailsDialog

import androidx.lifecycle.viewModelScope
import com.example.domain.entity.DishUiEntity
import com.example.domain.usecase.AddToBasketUseCase
import com.example.domain.usecase.GetBasketItemByIdUseCase
import com.example.domain.usecase.UpdateBasketItemUseCase
import com.example.foodorderapp.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishDetailsDialogViewModel @Inject constructor(
    private val addToBasketUseCase: AddToBasketUseCase,
    private val updateBasketItemUseCase: UpdateBasketItemUseCase,
    private val getBasketItemByIdUseCase: GetBasketItemByIdUseCase
) : BaseViewModel() {

    fun addToBasket(dishUiEntity: DishUiEntity) {
            getBasketItemByIdUseCase.invoke(dishUiEntity.id)
                .onEach { basketItem ->
                    if (basketItem != null) {
                        updateBasketItemUseCase.invoke(basketItem)
                    } else {
                        addToBasketUseCase.invoke(dishUiEntity, 1)
                    }
                }
                .catch {
                    it.printStackTrace()
                }.launchIn(viewModelScope)
    }
}