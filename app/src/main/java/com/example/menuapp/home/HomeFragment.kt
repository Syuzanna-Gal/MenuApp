package com.example.menuapp.home

import androidx.fragment.app.viewModels
import com.example.coreui.delegate.viewBinding
import com.example.coreui.extensions.addOnTabSelectedListener
import com.example.coreui.extensions.changeTabParams
import com.example.coreui.extensions.collectWhenStarted
import com.example.coreui.extensions.dp
import com.example.coreui.util.AdaptiveSpacingItemDecoration
import com.example.menuapp.R
import com.example.menuapp.core.base.BaseFragment
import com.example.menuapp.databinding.FragmentHomeBinding
import com.example.menuapp.home.adapter.BannerAdapter
import com.example.menuapp.home.adapter.DishesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : BaseFragment<HomeViewModel>(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModels()

    private val bannerAdapter by lazy {
        BannerAdapter(listOf())
    }

    private val dishesAdapter by lazy {
        DishesAdapter(onDishClick = {

        })
    }

    override fun initView() = with(binding) {
        tlCategories.addOnTabSelectedListener(
            onTabSelected = { tab ->
                viewModel.getDishesList(tab.text.toString())
            }
        )
        rvDishes.adapter = dishesAdapter.adapter
        vpBanner.adapter = bannerAdapter
        vpBanner.addItemDecoration(AdaptiveSpacingItemDecoration(10.dp, edgeEnabled = true))
        rvDishes.addItemDecoration(AdaptiveSpacingItemDecoration(16.dp))
    }

    override fun initObservers() {

        collectWhenStarted(viewModel.categoriesList) {
            it?.let {
                it.forEach { title ->
                    val tab = binding.tlCategories.newTab()
                    tab.text = title?.title
                    binding.tlCategories.addTab(tab)
                }
                binding.tlCategories.changeTabParams()
            }
        }

        collectWhenStarted(viewModel.dishesList) {
            dishesAdapter.adapter.submitList(it)
        }

        collectWhenStarted(viewModel.currentAddressDelegate.currentAddress) {
            binding.tvAddress.text = it.locality
        }

        collectWhenStarted(viewModel.bannerList) {
            bannerAdapter.submitList(it)
        }
    }
}