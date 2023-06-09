package com.example.foodorderapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coreui.extensions.collectWhenStarted
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.FragmentHomeBinding
import com.example.foodorderapp.home.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment() : Fragment(R.layout.fragment_home) {

    companion object {
        private const val DATE_FORMAT = "dd MMMM, yyyy"
        private const val DATE_LOCALE_TYPE = "ru"
    }

    private val binding by lazy {
        FragmentHomeBinding.inflate(LayoutInflater.from(requireContext()))
    }
    private val viewModel: HomeViewModel by viewModels()

    private val categoriesAdapter by lazy {
        CategoryAdapter(onCategoryClick = {})
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        viewModel.getCategoriesList()
        initObservers()
        rvCategory.adapter = categoriesAdapter.adapter
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale(DATE_LOCALE_TYPE))
        val current = formatter.format(time)
        tvTime.text = current
    }

    private fun initObservers() {
        collectWhenStarted(viewModel.categoriesList) {
            it?.let {
                categoriesAdapter.adapter.submitList(it)
            }
        }
    }
}