package com.example.takao.androidboilerplate.ui.second.third.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.takao.androidboilerplate.dao.entity.GithubOwner
import com.example.takao.androidboilerplate.dao.entity.GithubRepo

class GithubController(
    private val listener: CardClickListener
) : PagedListEpoxyController<GithubRepo>() {

    interface CardClickListener {
        fun onClickCard(userName: String)
    }

    var owners: List<GithubOwner> = emptyList()

    override fun buildItemModel(currentPosition: Int, item: GithubRepo?): EpoxyModel<*> {
        requireNotNull(item)
        return GithubRepositoryModel_().apply {
            id(currentPosition)
            repo = item
        }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        carousel {
            id("carousel")
            spanSizeOverride { _, _, _ -> 2 }
            paddingDp(8)
            numViewsToShowOnScreen(2f)
            models(
                owners.map {
                    GithubOwnerModel_().apply {
                        id(it.id)
                        owner = it
                        cardClickListener = listener::onClickCard
                    }
                }
            )
        }
        super.addModels(models)
    }
}
