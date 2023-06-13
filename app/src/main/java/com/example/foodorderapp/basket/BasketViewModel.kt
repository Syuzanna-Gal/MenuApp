package com.example.foodorderapp.basket

import androidx.lifecycle.viewModelScope
import com.example.domain.delegate.CurrentCityDelegate
import com.example.domain.entity.BasketItemUiEntity
import com.example.domain.usecase.RemoveFromBasketUseCase
import com.example.domain.usecase.SubscribeBasketItemsUseCase
import com.example.domain.usecase.UpdateBasketItemUseCase
import com.example.foodorderapp.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val subscribeBasketItemsUseCase: SubscribeBasketItemsUseCase,
    private val updateBasketItemUseCase: UpdateBasketItemUseCase,
    private val removeFromBasketUseCase: RemoveFromBasketUseCase,
    val currentCityDelegate: CurrentCityDelegate,
) : BaseViewModel() {

    private val _basketItemsList = MutableStateFlow<List<BasketItemUiEntity?>?>(null)
    val basketItemsList = _basketItemsList.asStateFlow()

    private val _paymentAmount = MutableStateFlow<Double?>(null)
    val paymentAmount = _paymentAmount.asStateFlow()

    init {
        getBasketItems()
    }

    fun getBasketItems() {
        subscribeBasketItemsUseCase.invoke()
            .onEach { basketItems ->
                _basketItemsList.value = basketItems
                getPaymentAmount(basketItems)
            }
            .catch {
                it.printStackTrace()
            }.launchIn(viewModelScope)
    }

    fun addItem(basketItemUiEntity: BasketItemUiEntity) {
        viewModelScope.launch {
            updateBasketItemUseCase(basketItemUiEntity)
        }
    }

    fun removeItem(basketItemUiEntity: BasketItemUiEntity) {
        viewModelScope.launch {
            removeFromBasketUseCase(basketItemUiEntity)
        }
    }

    private fun getPaymentAmount(list: List<BasketItemUiEntity?>) {
        var price = 0.0
        list.forEach {
            price += ((it?.price ?: 0.0) * (it?.quantity ?: 0))
        }
        _paymentAmount.value = price
    }
}