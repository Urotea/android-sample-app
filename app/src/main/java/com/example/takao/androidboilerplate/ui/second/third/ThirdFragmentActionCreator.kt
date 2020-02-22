package com.example.takao.androidboilerplate.ui.second.third

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.takao.androidboilerplate.actions.AppActions
import com.example.takao.androidboilerplate.dao.GithubApi
import com.example.takao.androidboilerplate.store.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThirdFragmentActionCreator @Inject constructor(
    private val dispatcher: Dispatcher<AppActions>,
    private val api: GithubApi
) {
    fun ownerSelected(userName: String, scope: LifecycleCoroutineScope) {
        scope.launch(Dispatchers.IO) {
            val response = api.getOwner(userName)
            if (response.isSuccessful && response.body() != null) {
                dispatcher.dispatch(AppActions.OwnerDataLoaded(response.body()!!))
            }
        }
    }

    fun setOwnerList(ownerList: List<String>, scope: LifecycleCoroutineScope) {
        scope.launch(Dispatchers.IO) {
            val result = ownerList.mapNotNull { ownerName ->
                val response = api.getOwner(ownerName)
                if (response.isSuccessful && response.body() != null) {
                    response.body()
                } else null
            }
            this@ThirdFragmentActionCreator.dispatcher.dispatch(AppActions.SetOwnerList(result))
        }
    }
}