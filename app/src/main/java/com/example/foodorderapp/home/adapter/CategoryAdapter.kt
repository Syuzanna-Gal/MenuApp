package com.example.foodorderapp.home.adapter

import android.view.View
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.domain.entity.CategoryUiEntity
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.ItemCategoryBinding
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class CategoryAdapter(
    private val onCategoryClick: (String) -> Unit
) {

    companion object {
        private const val IMAGE_CORNER_RADIUS = 10F
    }

    val adapter = adapterOf<CategoryUiEntity> {
        diff(
            areItemsTheSame = { old, new ->
                old.id == new.id
            },
            areContentsTheSame = { old, new ->
                old == new
            }
        )
        register(
            layoutResource = R.layout.item_category,
            viewHolder = {
                CategoryViewHolder(it, onCategoryClick)
            },
            onBindViewHolder = { vh, _, p -> vh.onBind(p) }
        )
    }

    class CategoryViewHolder(
        view: View,
        private val onCategoryClick: (String) -> Unit
    ) : RecyclerViewHolder<CategoryUiEntity>(view) {

        private val binding = ItemCategoryBinding.bind(view)

        fun onBind(item: CategoryUiEntity) = with(binding) {
            tvCategoryName.text = item.title
            ivCategoryImage.load(item.imageUrl) {
                transformations(RoundedCornersTransformation((IMAGE_CORNER_RADIUS * itemView.context.resources.displayMetrics.density)))
            }
            itemView.setOnClickListener {
                onCategoryClick(item.title)
            }
        }
    }
}