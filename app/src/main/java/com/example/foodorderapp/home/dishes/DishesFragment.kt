package com.example.foodorderapp.home.dishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.coreui.extensions.collectWhenStarted
import com.example.coreui.util.USER_PIC_IMAGE_RADIUS
import com.example.coreui.util.USER_PIC_URL
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.FragmentDishesBinding
import com.example.foodorderapp.home.adapter.DishesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishesFragment : Fragment() {

    private val binding by lazy {
        FragmentDishesBinding.inflate(LayoutInflater.from(requireContext()))
    }
    private val viewModel: DishesViewModel by viewModels()
    private val navArgs by navArgs<DishesFragmentArgs>()
    private val dishesAdapter by lazy {
        DishesAdapter(onDishClick = {
            Navigation.findNavController(requireActivity(), R.id.mainNavContainer)
                .navigate(DishesFragmentDirections.toDishDetailsDialogFragment(it))
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        viewModel.getDishesList()
        initObservers()
        ivUserPic.load(USER_PIC_URL) {
            transformations(RoundedCornersTransformation(USER_PIC_IMAGE_RADIUS))
        }
        btnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
        tvTitle.text = navArgs.title
        rvDishes.adapter = dishesAdapter.adapter
    }

    private fun initObservers() {
        collectWhenStarted(viewModel.dishesList) {
            dishesAdapter.adapter.submitList(it)
        }
    }
}