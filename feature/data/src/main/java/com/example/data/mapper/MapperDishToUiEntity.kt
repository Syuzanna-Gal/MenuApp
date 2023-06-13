package com.example.data.mapper


import com.example.data.remote.entity.DishEntity
import com.example.domain.entity.DishUiEntity

internal class MapperDishToUiEntity : Mapper<DishEntity, DishUiEntity> {
    override fun map(from: DishEntity): DishUiEntity {
        return DishUiEntity(
            id = from.id,
            name = from.name.orEmpty(),
            price = from.price,
            weight = from.weight,
            description = from.description.orEmpty(),
            imageUrl = from.imageUrl.orEmpty(),
            tags = from.tags
        )
    }
}