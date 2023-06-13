package com.example.data.mapper


import com.example.data.remote.entity.CategoryEntity
import com.example.domain.entity.CategoryUiEntity

internal class MapperCategoryToUiEntity : Mapper<CategoryEntity, CategoryUiEntity> {
    override fun map(from: CategoryEntity?): CategoryUiEntity {
        return CategoryUiEntity(
            id = from?.id ?: 0,
            title = from?.name.orEmpty(),
            imageUrl = from?.imageUrl.orEmpty()
        )
    }
}