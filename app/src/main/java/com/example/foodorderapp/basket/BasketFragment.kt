package com.example.foodorderapp.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.FragmentBasketBinding

class BasketFragment: Fragment(R.layout.fragment_basket) {

    private val binding by lazy {
        FragmentBasketBinding.inflate(LayoutInflater.from(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}