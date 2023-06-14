package com.example.foodorderapp.home.dishes.dish_details_dialog

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.coreui.delegate.viewBinding
import com.example.foodorderapp.MainActivity
import com.example.foodorderapp.R
import com.example.foodorderapp.core.base.BaseDialogFragment
import com.example.foodorderapp.databinding.DialogDishDetailsBinding
import com.example.foodorderapp.util.type_alias.RString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DishDetailsDialogFragment :
    BaseDialogFragment<DishDetailsDialogViewModel>(R.layout.dialog_dish_details) {

    private val binding by viewBinding(DialogDishDetailsBinding::bind)
    override val viewModel: DishDetailsDialogViewModel by viewModels()
    private val navArgs by navArgs<DishDetailsDialogFragmentArgs>()

    override fun initView() = with(binding) {
        val dish = navArgs.dish
        tvName.text = dish.name
        ivDish.load(dish.imageUrl)
        tvDescription.text = dish.description
        val priceAndWeightTitle =
            requireContext().getString(RString.price_and_weight)
                .format(dish.price.toInt().toString(), dish.weight.toInt().toString())
        val outPutColoredText: Spannable = SpannableString(priceAndWeightTitle)
        outPutColoredText.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.black)),
            0,
            priceAndWeightTitle.indexOf("₽") + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tvPriceAndWeight.text = outPutColoredText
        btnCancel.setOnClickListener {
            dialog?.dismiss()
        }
        btnAddToBasket.setOnClickListener {
            viewModel.addToBasket(dish)
            dialog?.dismiss()
            findNavController().popBackStack()
            (activity as MainActivity).changeTab(R.id.basket_graph)
        }
    }
}