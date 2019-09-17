package com.example.takao.androidboilerplate.ui.next

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.takao.androidboilerplate.actions.AppActions
import com.example.takao.androidboilerplate.repository.PingPongRepository
import com.example.takao.androidboilerplate.store.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import javax.inject.Inject

class NextFragmentActionCreator @Inject constructor(
    private val dispatcher: Dispatcher<AppActions>,
    private val pingPongRepository: PingPongRepository
) {
    fun pingPong(lifecycleScope: LifecycleCoroutineScope) = lifecycleScope.launch(Dispatchers.IO) {
        this@NextFragmentActionCreator.dispatcher.dispatch(
            AppActions.StartPingPong(OffsetDateTime.now()))
        val pingPong = this@NextFragmentActionCreator.pingPongRepository.getNewPingPongStatus()
        this@NextFragmentActionCreator.dispatcher.dispatch(AppActions.PongNetworkResponseReceived(pingPong = pingPong))
    }
}