package com.example.menuapp.home.adapter

import android.view.View
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.domain.entity.DishUiEntity
import com.example.menuapp.R
import com.example.menuapp.databinding.ItemDishBinding
import com.example.menuapp.util.type_alias.RString
import me.ibrahimyilmaz.kiel.adapterOf
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class DishesAdapter(
    private val onDishClick: (id: DishUiEntity) -> Unit
) {

    val adapter = adapterOf<DishUiEntity> {
        diff(
            areItemsTheSame = { old, new ->
                old.id == new.id
            },
            areContentsTheSame = { old, new ->
                old == new
            }
        )
        register(
            layoutResource = R.layout.item_dish,
            viewHolder = {
                DishViewHolder(it, onDishClick)
            },
            onBindViewHolder = { vh, _, p -> vh.onBind(p) }
        )
    }

    class DishViewHolder(
        view: View,
        private val onDishClick: (id: DishUiEntity) -> Unit
    ) : RecyclerViewHolder<DishUiEntity>(view) {

        private val binding = ItemDishBinding.bind(view)

        fun onBind(item: DishUiEntity) = with(binding) {
            tvName.text = item.name
            ivDish.load(item.pic) {
                transformations(RoundedCornersTransformation(50f))
            }
            tvDescription.text = item.description
            tvPrice.text = itemView.context.getString(RString.meal_price, item.price.toString())
        }
    }
}