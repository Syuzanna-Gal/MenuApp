package com.example.foodorderapp.profile

import androidx.fragment.app.viewModels
import com.example.coreui.delegate.viewBinding
import com.example.foodorderapp.R
import com.example.foodorderapp.core.base.BaseFragment
import com.example.foodorderapp.databinding.FragmentBasketBinding
import com.example.foodorderapp.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<ProfileViewModel>(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    override val viewModel: ProfileViewModel by viewModels()

}