package com.example.foodorderapp.basket

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.coreui.delegate.viewBinding
import com.example.coreui.extensions.collectWhenStarted
import com.example.coreui.extensions.dp
import com.example.coreui.extensions.getCurrentDate
import com.example.coreui.util.AdaptiveSpacingItemDecoration
import com.example.coreui.util.USER_PIC_IMAGE_RADIUS
import com.example.coreui.util.USER_PIC_URL
import com.example.foodorderapp.R
import com.example.foodorderapp.basket.adapter.BasketItemsAdapter
import com.example.foodorderapp.core.base.BaseFragment
import com.example.foodorderapp.databinding.FragmentBasketBinding
import com.example.foodorderapp.util.RString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<BasketViewModel>(R.layout.fragment_basket) {

    private val binding by viewBinding(FragmentBasketBinding::bind)
    override val viewModel: BasketViewModel by viewModels()

    private val basketAdapter by lazy {
        BasketItemsAdapter(onAddClick = {
            viewModel.addItem(it)
        }, onRemoveClick = {
            viewModel.removeItem(it)
        })
    }

    override fun initView() = with(binding) {
        viewModel.getBasketItems()
        initObservers()
        ivUserPic.load(USER_PIC_URL) {
            transformations(RoundedCornersTransformation(USER_PIC_IMAGE_RADIUS))
        }
        tvTime.text = getCurrentDate()
        rvCategory.adapter = basketAdapter.adapter
        rvCategory.addItemDecoration(AdaptiveSpacingItemDecoration(16.dp, edgeEnabled = true))
        btnPay.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(RString.payed_successfully),
                Toast.LENGTH_LONG
            ).show()
            viewModel.removeAll()
        }
    }

    override fun initObservers() {
        collectWhenStarted(viewModel.basketItemsList) {
            it?.let {
                basketAdapter.adapter.submitList(it)
                binding.tvBasketIsEmpty.isVisible = it.isEmpty() == true
                binding.btnPay.isVisible = it.isEmpty() == false
            }
        }

        collectWhenStarted(viewModel.paymentAmount) {
            it?.let {
                binding.btnPay.text =
                    getString(RString.pay).format(it.toInt().toString())
            }
        }

        collectWhenStarted(viewModel.currentCityDelegate.currentCity) {
            binding.tvAddress.text = it
        }
    }

}