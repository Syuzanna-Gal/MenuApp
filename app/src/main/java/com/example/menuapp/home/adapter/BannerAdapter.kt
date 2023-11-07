package com.example.menuapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.menuapp.R
import com.example.menuapp.databinding.ItemBannerBinding

class BannerAdapter(private var cardList: List<String>) :
    RecyclerView.Adapter<BannerAdapter.CardViewHolder>() {

    fun submitList(cardList: List<String>) {
        this.cardList = cardList
        this.notifyItemRangeChanged(0, cardList.size)
    }

    fun getItem(index: Int): String? = this.cardList.getOrNull(index)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_banner, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int = cardList.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(cardList[holder.adapterPosition])
    }

    class CardViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {

        private val binding = ItemBannerBinding.bind(view)

        fun onBind(item: String) {
            binding.ivBanner.load(item) {
                transformations(RoundedCornersTransformation(30f))
            }
        }
    }
}