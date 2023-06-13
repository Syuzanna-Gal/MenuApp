package com.example.foodorderapp.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.coreui.extensions.collectWhenStarted
import com.example.coreui.extensions.navigateSafe
import com.example.foodorderapp.core.navigation.Command

abstract class BaseDialogFragment<VM : BaseViewModel>(@LayoutRes layout: Int) :
    DialogFragment(layout) {

    abstract val viewModel: VM

    private var navController: NavController? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NORMAL, com.example.coreui.R.style.DialogTheme)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        collectWhenStarted(viewModel.command) { processCommand(it) }
        initView()
        initObservers()
    }

    protected open fun initView() {}
    protected open fun initObservers() {}

    protected open fun processCommand(command: Command) {
        when (command) {
            is Command.FinishAppCommand -> activity?.finishAffinity()
            is Command.NavigateUpCommand -> navController?.popBackStack()
            is Command.NavCommand -> if (command.isNested)
                getParentNavController()?.navigateSafe(command.navDirections)
            else
                navController?.navigateSafe(command.navDirections)
        }
    }

    private fun getParentNavController() = parentFragment?.parentFragment
        ?.parentFragmentManager?.primaryNavigationFragment?.findNavController()
}