package com.example.foodorderapp.home.dishes.dish_details_dialog

import androidx.lifecycle.viewModelScope
import com.example.domain.delegate.ChangeTabDelegate
import com.example.domain.entity.BasketItemUiEntity
import com.example.domain.entity.DishUiEntity
import com.example.domain.usecase.AddToBasketUseCase
import com.example.domain.usecase.GetBasketItemByIdUseCase
import com.example.domain.usecase.UpdateBasketItemUseCase
import com.example.foodorderapp.R
import com.example.foodorderapp.core.base.BaseViewModel
import com.example.foodorderapp.util.TextSource
import com.example.foodorderapp.util.event.InfoEvent
import com.example.foodorderapp.util.type_alias.RString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishDetailsDialogViewModel @Inject constructor(
    private val addToBasketUseCase: AddToBasketUseCase,
    private val updateBasketItemUseCase: UpdateBasketItemUseCase,
    private val getBasketItemByIdUseCase: GetBasketItemByIdUseCase,
    private val changeTabDelegate: ChangeTabDelegate
) : BaseViewModel() {

    private val _successfullyAdded = Channel<Unit>(Channel.CONFLATED)
    val successfullyAdded = _successfullyAdded.receiveAsFlow()

    fun addToBasket(dishUiEntity: DishUiEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            var basketItem: BasketItemUiEntity? = null
            try {
                basketItem = getBasketItemByIdUseCase.invoke(dishUiEntity.id)
            } catch (e: java.lang.Exception) {
                emitInfoEvent(
                    InfoEvent.Info(
                        title = TextSource.Resource(R.string.something_went_wrong),
                        message = TextSource.Resource(RString.get_item_error),
                        buttonText = TextSource.Resource(R.string.ok)
                    )
                )
            }

            if (basketItem != null) {
                try {
                    updateBasketItemUseCase.invoke(basketItem)
                    _successfullyAdded.send(Unit)
                } catch (e: java.lang.Exception) {
                    emitInfoEvent(
                        InfoEvent.Info(
                            title = TextSource.Resource(R.string.something_went_wrong),
                            message = TextSource.Resource(RString.update_item_error),
                            buttonText = TextSource.Resource(R.string.ok)
                        )
                    )
                }
            } else {
                try {
                    addToBasketUseCase.invoke(dishUiEntity, 1)
                    _successfullyAdded.send(Unit)
                } catch (e: java.lang.Exception) {
                    emitInfoEvent(
                        InfoEvent.Info(
                            title = TextSource.Resource(R.string.something_went_wrong),
                            message = TextSource.Resource(RString.add_item_error),
                            buttonText = TextSource.Resource(R.string.ok)
                        )
                    )
                }
            }
        }
    }

    fun changeCurrentTab(tabRes: Int) {
        viewModelScope.launch {
            changeTabDelegate.setCurrentTabRes(tabRes)
        }
    }
}