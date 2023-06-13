package com.example.data.mapper

import com.example.data.local.db.entity.BasketItemDbEntity
import com.example.domain.entity.BasketItemUiEntity

class MapperBasketItemToDbEntity : Mapper<BasketItemUiEntity, BasketItemDbEntity> {
    override fun map(from: BasketItemUiEntity): BasketItemDbEntity {
        return BasketItemDbEntity(
            id = from.id,
            name = from.name,
            imageUrl = from.imageUrl,
            price = from.price,
            weight = from.weight,
            quantity = from.quantity,
            createdAt = System.currentTimeMillis()
        )
    }
}