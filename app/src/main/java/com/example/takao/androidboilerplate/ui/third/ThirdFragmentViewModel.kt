package com.example.takao.androidboilerplate.ui.third

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.takao.androidboilerplate.dao.GithubApi
import com.example.takao.androidboilerplate.dao.entity.GithubOwner
import com.example.takao.androidboilerplate.dao.entity.GithubRepo
import com.example.takao.androidboilerplate.state.AppState
import com.example.takao.androidboilerplate.store.StateAccessor
import com.example.takao.androidboilerplate.ui.third.paging.DataSourceFactory
import javax.inject.Inject

class ThirdFragmentViewModel @Inject constructor(
    stateAccessor: StateAccessor<AppState>,
    private val api: GithubApi // RepositoryPatternにしたい
) : ViewModel() {
    val repos: LiveData<PagedList<GithubRepo>> =
        switchMap(stateAccessor.state) { appState ->
            val dataSourceFactory = DataSourceFactory(appState.thirdFragmentState.selectedName, api, viewModelScope)
            val config = PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .build()

            LivePagedListBuilder(dataSourceFactory, config).build()
        }

    val owner: LiveData<List<GithubOwner>> = switchMap(stateAccessor.state) { appState ->
        liveData(viewModelScope.coroutineContext) {
            val result = appState.thirdFragmentState.ownerList.mapNotNull { ownerName ->
                val response = api.getOwner(ownerName)
                if (response.isSuccessful && response.body() != null) {
                    response.body()
                } else null
            }
            emit(result)
        }
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}