package com.example.menuapp.home

import androidx.lifecycle.viewModelScope
import com.example.coreui.util.BANNER_PIC_URL
import com.example.domain.delegate.CurrentAddressDelegate
import com.example.domain.entity.CategoryUiEntity
import com.example.domain.entity.DishUiEntity
import com.example.domain.usecase.GetCategoryUseCase
import com.example.domain.usecase.GetDishesUseCase
import com.example.menuapp.R
import com.example.menuapp.core.base.BaseViewModel
import com.example.menuapp.util.TextSource
import com.example.menuapp.util.event.InfoEvent
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
    val currentAddressDelegate: CurrentAddressDelegate,
    private val getDishesUseCase: GetDishesUseCase,
) : BaseViewModel() {

    private val _dishesList = MutableStateFlow<List<DishUiEntity?>?>(null)
    val dishesList = _dishesList.asStateFlow()

    private val _categoriesList = MutableStateFlow<List<CategoryUiEntity?>?>(null)
    val categoriesList = _categoriesList.asStateFlow()

    private val _bannerList = MutableStateFlow<List<String>>(emptyList())
    val bannerList = _bannerList.asStateFlow()

    init {
        getCategories()
        getBanners()
    }

    fun getDishesList(category: String) {
        getDishesUseCase(category)
            .onEach { dishes ->
                _dishesList.value = dishes
            }
            .catch {
                emitInfoEvent(
                    InfoEvent.Info(
                        title = TextSource.Resource(R.string.something_went_wrong),
                        message = TextSource.Resource(R.string.something_went_wrong),
                        buttonText = TextSource.Resource(R.string.ok)
                    )
                )
            }.launchIn(viewModelScope)
    }


    private fun getBanners() {
        _bannerList.value = listOf(
            BANNER_PIC_URL,
            BANNER_PIC_URL,
            BANNER_PIC_URL,
        )
    }

    private fun getCategories() {
        getCategoryUseCase()
            .onEach {
                _categoriesList.value = it
            }
            .catch {
                emitInfoEvent(
                    InfoEvent.Info(
                        title = TextSource.Resource(R.string.something_went_wrong),
                        message = TextSource.Dynamic(it.message ?: ""),
                        buttonText = TextSource.Resource(R.string.ok)
                    )
                )
            }
            .launchIn(viewModelScope)
    }

}