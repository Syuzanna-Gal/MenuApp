package com.example.foodorderapp.util.info

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.coreui.delegate.viewBinding
import com.example.coreui.extensions.parcelableOrThrow
import com.example.foodorderapp.R
import com.example.foodorderapp.core.base.BaseBottomSheetDialogFragment
import com.example.foodorderapp.databinding.BottomSheetInfoBinding
import com.example.foodorderapp.util.type_alias.RString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoBottomSheetDialog :
    BaseBottomSheetDialogFragment<BottomSheetInfoBinding, InfoDialogViewModel>() {

    override val viewModel: InfoDialogViewModel by viewModels()
    override val layoutId: Int = R.layout.bottom_sheet_info
    private val binding by viewBinding(BottomSheetInfoBinding::bind)
    private val args by lazy { arguments.parcelableOrThrow<InfoDialogArgs>(KEY_ARGS) }

    override fun initView() = with(binding) {
        tvTitle.text = args.title
        tvMessage.text = args.message
        btnAction.text = args.buttonText
        btnAction.setOnClickListener {
            if (args.buttonText == getString(RString.settings)) {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                val uri = Uri.fromParts("package", context?.packageName, null);
                intent.data = uri;
                startActivity(intent)
            } else {
                dismiss()
            }
        }
    }

    companion object {
        private const val KEY_ARGS = "key_args"
        const val TAG = "InfoBottomSheetDialog"

        fun newInstance(args: InfoDialogArgs) = InfoBottomSheetDialog().apply {
            arguments = bundleOf(KEY_ARGS to args)
        }
    }
}