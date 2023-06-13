package com.example.foodorderapp.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coreui.delegate.viewBinding
import com.example.foodorderapp.R
import com.example.foodorderapp.core.base.BaseFragment
import com.example.foodorderapp.databinding.FragmentBasketBinding
import com.example.foodorderapp.databinding.FragmentSearchBinding
import com.example.foodorderapp.profile.ProfileViewModel

class SearchFragment : BaseFragment<SearchViewModel>(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    override val viewModel: SearchViewModel by viewModels()

}