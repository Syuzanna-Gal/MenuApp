package com.example.foodorderapp.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.coreui.extensions.collectWhenStarted
import com.example.coreui.extensions.getCurrentDate
import com.example.coreui.util.USER_PIC_IMAGE_RADIUS
import com.example.coreui.util.USER_PIC_URL
import com.example.foodorderapp.R
import com.example.foodorderapp.basket.adapter.BasketItemsAdapter
import com.example.foodorderapp.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.fragment_basket) {

    private val binding by lazy {
        FragmentBasketBinding.inflate(LayoutInflater.from(requireContext()))
    }
    private val viewModel: BasketViewModel by viewModels()

    private val basketAdapter by lazy {
        BasketItemsAdapter(onAddClick = {}, onRemoveClick = {})
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        viewModel.getBasketItems()
        initObservers()
        ivUserPic.load(USER_PIC_URL) {
            transformations(RoundedCornersTransformation(USER_PIC_IMAGE_RADIUS))
        }
        tvTime.text = getCurrentDate()
        rvCategory.adapter = basketAdapter.adapter
    }

    private fun initObservers() {
        collectWhenStarted(viewModel.basketItemsList) {
            basketAdapter.adapter.submitList(it)
        }

        collectWhenStarted(viewModel.paymentAmount) {
            it?.let {
                binding.btnPay.text =
                    getString(com.example.coreui.R.string.pay).format(it.toString())
            }
        }
    }

}