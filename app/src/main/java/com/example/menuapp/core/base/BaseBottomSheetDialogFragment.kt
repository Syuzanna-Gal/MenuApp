package com.example.menuapp.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.coreui.extensions.collectWhenStarted
import com.example.coreui.extensions.navigateSafe
import com.example.menuapp.core.navigation.Command
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<VB : ViewBinding, VM : BaseViewModel> :
    BottomSheetDialogFragment() {

    abstract val viewModel: VM
    abstract val layoutId: Int

    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        initView()
        initObservers()
        collectWhenStarted(viewModel.command) { processCommand(it) }
    }

    open fun initView() {}
    open fun initObservers() {}

    protected open fun processCommand(command: Command) {
        when (command) {
            is Command.FinishAppCommand -> activity?.finishAffinity()
            is Command.NavigateUpCommand -> navController?.popBackStack()
            is Command.NavCommand -> navController?.navigateSafe(command.navDirections)
        }
    }
}