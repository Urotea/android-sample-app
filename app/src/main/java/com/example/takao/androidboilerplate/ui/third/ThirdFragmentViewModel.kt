package com.example.takao.androidboilerplate.ui.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.takao.androidboilerplate.dao.GithubApi
import com.example.takao.androidboilerplate.dao.entity.GithubOwner
import com.example.takao.androidboilerplate.dao.entity.GithubRepo
import com.example.takao.androidboilerplate.state.AppState
import com.example.takao.androidboilerplate.store.StateAccessor
import com.example.takao.androidboilerplate.ui.third.paging.DataSourceFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThirdFragmentViewModel @Inject constructor(
    private val stateAccessor: StateAccessor<AppState>,
    private val api: GithubApi // RepositoryPatternにしたい
): ViewModel() {
    private val _name = MutableLiveData<String>()

    val repos: LiveData<PagedList<GithubRepo>> =
        switchMap(_name) { username ->
            val dataSourceFactory = DataSourceFactory(username, api, viewModelScope)

            val config = PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .build()

            LivePagedListBuilder(dataSourceFactory, config).build()
        }

    private val _owner = MutableLiveData<List<GithubOwner>>()
    val owner: LiveData<List<GithubOwner>>
        get() = _owner

    fun setUsername(username: String) = viewModelScope.launch {
        _name.postValue(username)
    }

    fun getOwner() {
        //　今回はここで決め打ち
        val usernames = listOf("Urotea", "googlesamples", "kotlin", "android", "jetbrains")
        viewModelScope.launch {
            val ownerList = ArrayList<GithubOwner>()
            usernames.forEach { username ->
                try {
                    val response = api.getOwner(username)
                    if (response.isSuccessful && response.body() != null) {
                        ownerList.add(response.body()!!)
                    }
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }
            _owner.postValue(ownerList)
            setUsername(usernames[0])
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}