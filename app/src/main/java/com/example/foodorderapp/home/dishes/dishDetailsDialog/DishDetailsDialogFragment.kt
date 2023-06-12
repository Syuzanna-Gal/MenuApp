package com.example.foodorderapp.home.dishes.dishDetailsDialog

import android.app.Dialog
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.DialogDishDetailsBinding

class DishDetailsDialogFragment : DialogFragment() {

    private val binding by lazy {
        DialogDishDetailsBinding.inflate(LayoutInflater.from(requireContext()))
    }
    private val navArgs by navArgs<DishDetailsDialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NORMAL, com.example.coreui.R.style.DialogTheme)

        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        val dish = navArgs.dish
        tvName.text = dish.name
        ivDish.load(dish.imageUrl)
        tvDescription.text = dish.description
        val priceAndWeightTitle =
            requireContext().getString(com.example.coreui.R.string.price_and_weight)
                .format(dish.price.toString(), dish.weight.toString())
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
    }
}