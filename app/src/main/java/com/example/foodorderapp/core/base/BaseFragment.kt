package com.example.foodorderapp.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.example.coreui.extensions.collectWhenStarted
import com.example.coreui.extensions.navigateSafe
import com.example.foodorderapp.core.navigation.Command

abstract class BaseFragment<VM>(@LayoutRes layout: Int) : Fragment(layout)
        where VM : BaseViewModel {

    abstract val viewModel: VM

    private var navController: NavController? = null
    protected open val navControllerRes: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = getNavController()
        collectWhenStarted(viewModel.command, ::processCommand)
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

    private fun getNavController() = try {
        navControllerRes?.let { resId ->
            Navigation.findNavController(requireActivity(), resId)
        } ?: findNavController()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    private fun getParentNavController() = parentFragment?.parentFragment
        ?.parentFragmentManager?.primaryNavigationFragment?.findNavController()
}