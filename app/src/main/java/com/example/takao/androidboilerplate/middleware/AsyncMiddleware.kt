package com.example.takao.androidboilerplate.middleware

import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.redux.Middleware
import com.example.takao.androidboilerplate.repository.PingPongRepository
import com.example.takao.androidboilerplate.store.MainActivityState
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

class AsyncMiddleware @Inject constructor(
    private val pingPongRepository: PingPongRepository
) : Middleware<MainActivityActions, MainActivityState> {
    override fun apply(
        actionStream: Flowable<MainActivityActions>,
        stateStream: Flowable<MainActivityState>
    ): Flowable<MainActivityActions> {
        val shared = actionStream.share()
        return doWhenPingButtonClicked(shared)
    }

    private fun doWhenPingButtonClicked(action: Flowable<MainActivityActions>): Flowable<MainActivityActions> {
        val filtered = action.filter { it is MainActivityActions.PingButtonClicked }
            .flatMap { action ->
                Flowable.concat(
                    Flowable.just(action),
                    Flowable.create<MainActivityActions>({ emitter ->
                        emitter.onNext(
                            MainActivityActions.PongNetworkResponseReceived(
                                this.pingPongRepository.getNewPingPongStatus()
                            )
                        )
                    }, BackpressureStrategy.LATEST)
                )
            }

        val other = action.filter { it !is MainActivityActions.PingButtonClicked }

        return Flowable.merge(filtered, other)
    }
}
