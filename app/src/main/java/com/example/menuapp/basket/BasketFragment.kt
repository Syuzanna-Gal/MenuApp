package com.example.menuapp.basket

import androidx.fragment.app.viewModels
import com.example.coreui.delegate.viewBinding
import com.example.menuapp.R
import com.example.menuapp.core.base.BaseFragment
import com.example.menuapp.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<BasketViewModel>(R.layout.fragment_basket) {

    private val binding by viewBinding(FragmentBasketBinding::bind)
    override val viewModel: BasketViewModel by viewModels()

}