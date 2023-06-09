package com.example.foodorderapp.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by lazy {
        FragmentSearchBinding.inflate(LayoutInflater.from(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}