package com.example.foodorderapp.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.coreui.extensions.collectWhenStarted
import com.example.coreui.extensions.navigateSafe
import com.example.foodorderapp.core.navigation.Command
import com.example.foodorderapp.util.RStyle

abstract class BaseDialogFragment<VM : BaseViewModel>(@LayoutRes layout: Int) :
    DialogFragment(layout) {

    abstract val viewModel: VM

    private var navController: NavController? = null
    protected open val navControllerRes: Int? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NORMAL, RStyle.DialogTheme)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = getNavController()
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
            is Command.NavCommand -> {
                navController?.navigateSafe(command.navDirections)
            }
        }
    }

    private fun getNavController() = try {
        navControllerRes?.let { resId ->
            Navigation.findNavController(requireActivity(), resId)
        } ?: findNavController()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}