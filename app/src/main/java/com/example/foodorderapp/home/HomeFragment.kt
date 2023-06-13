package com.example.foodorderapp.home

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
import com.example.foodorderapp.core.base.BaseFragment
import com.example.foodorderapp.databinding.FragmentHomeBinding
import com.example.foodorderapp.home.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : BaseFragment<HomeViewModel>(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModels()

    private val categoriesAdapter by lazy {
        CategoryAdapter(onCategoryClick = {
            viewModel.navigateToDishFragment(it)
        })
    }

    override fun initView() = with(binding) {
        rvCategory.adapter = categoriesAdapter.adapter
        rvCategory.addItemDecoration(AdaptiveSpacingItemDecoration(8.dp, edgeEnabled = true))
        ivUserPic.load(USER_PIC_URL) {
            transformations(RoundedCornersTransformation(USER_PIC_IMAGE_RADIUS))
        }
        tvTime.text = getCurrentDate()
    }

    override fun initObservers() {
        collectWhenStarted(viewModel.categoriesList) {
            it?.let {
                categoriesAdapter.adapter.submitList(it)
            }
        }
        collectWhenStarted(viewModel.currentCityDelegate.currentCity) {
            binding.tvAddress.text = it
        }
    }
}