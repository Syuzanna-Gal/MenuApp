package com.example.menuapp.util.info_event

import androidx.fragment.app.Fragment
import com.example.coreui.extensions.collectWhenStarted
import com.example.menuapp.util.event.InfoEvent
import com.example.menuapp.util.info.InfoBottomSheetDialog
import com.example.menuapp.util.info.InfoDialogArgs
import kotlinx.coroutines.flow.Flow

//TODO: generic solution for lifecycle owners
interface InfoEventCollector {

    fun collectInfoEvents(host: Fragment, events: Flow<InfoEvent>) {
        host.collectWhenStarted(events) { event ->
            onEventReceived(host, event)
        }
    }

    fun onEventReceived(host: Fragment, event: InfoEvent)
}

class InfoEventCollectorImpl : InfoEventCollector {

    override fun onEventReceived(host: Fragment, event: InfoEvent) = when (event) {
        is InfoEvent.Info -> onBottomSheetInfoEvent(host, event)
    }

    private fun onBottomSheetInfoEvent(host: Fragment, event: InfoEvent.Info) {
        val args = event.run {
            InfoDialogArgs(
                title = title.asString(host.requireContext()),
                message = message.asString(host.requireContext()),
                buttonText = buttonText.asString(host.requireContext())
            )
        }

        InfoBottomSheetDialog
            .newInstance(args)
            .show(host.childFragmentManager, InfoBottomSheetDialog.TAG)
    }

}