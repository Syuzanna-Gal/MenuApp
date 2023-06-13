package com.example.data.mapper

import com.example.data.local.db.entity.BasketItemDbEntity
import com.example.domain.entity.BasketItemUiEntity

class MapperBasketItemToUiEntity : Mapper<BasketItemDbEntity, BasketItemUiEntity?> {
    override fun map(from: BasketItemDbEntity?): BasketItemUiEntity? {
        return if (from == null)
            null
        else
            BasketItemUiEntity(
                id = from.id,
                name = from.name,
                imageUrl = from.imageUrl,
                price = from.price,
                weight = from.weight,
                quantity = from.quantity
            )
    }
}