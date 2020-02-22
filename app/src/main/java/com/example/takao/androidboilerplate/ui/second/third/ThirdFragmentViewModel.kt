package com.example.takao.androidboilerplate.ui.second.third

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.takao.androidboilerplate.dao.GithubApi
import com.example.takao.androidboilerplate.dao.entity.GithubOwner
import com.example.takao.androidboilerplate.dao.entity.GithubRepo
import com.example.takao.androidboilerplate.state.AppState
import com.example.takao.androidboilerplate.store.StateAccessor
import com.example.takao.androidboilerplate.ui.second.third.paging.DataSourceFactory
import javax.inject.Inject

class ThirdFragmentViewModel @Inject constructor(
    stateAccessor: StateAccessor<AppState>,
    private val api: GithubApi // RepositoryPatternにしたい
) : ViewModel() {
    val repos: LiveData<PagedList<GithubRepo>> =
        switchMap(Transformations.distinctUntilChanged(Transformations.map(stateAccessor.state) {
            it.thirdFragmentState.selectedName
        })) { selectedName ->
            val dataSourceFactory = DataSourceFactory(selectedName, api, viewModelScope)
            val config = PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .build()

            LivePagedListBuilder(dataSourceFactory, config).build()
        }

    val owner: LiveData<List<GithubOwner>> =
        Transformations.distinctUntilChanged(
            Transformations.map(stateAccessor.state) {
                it.thirdFragmentState.ownerList
            })

    companion object {
        private const val PAGE_SIZE = 10
    }
}