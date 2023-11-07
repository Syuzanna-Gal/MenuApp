package com.example.menuapp.profile

import androidx.fragment.app.viewModels
import com.example.coreui.delegate.viewBinding
import com.example.menuapp.R
import com.example.menuapp.core.base.BaseFragment
import com.example.menuapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileViewModel>(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    override val viewModel: ProfileViewModel by viewModels()

}