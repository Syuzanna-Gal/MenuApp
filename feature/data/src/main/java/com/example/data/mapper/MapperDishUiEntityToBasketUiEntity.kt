package com.example.data.mapper

import com.example.data.local.db.entity.BasketItemDbEntity
import com.example.domain.entity.DishUiEntity
import com.example.domain.mapper.Mapper

class MapperDishUiEntityToBasketItemDbEntity(private val quantity: Int) :
    Mapper<DishUiEntity, BasketItemDbEntity> {
    override fun map(from: DishUiEntity): BasketItemDbEntity {
        return BasketItemDbEntity(
            id = from.id,
            name = from.name,
            imageUrl = from.imageUrl,
            price = from.price,
            weight = from.weight,
            quantity = quantity,
            createdAt = System.currentTimeMillis()
        )
    }
}