package com.example.takao.androidboilerplate.ui.third.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.takao.androidboilerplate.R
import com.example.takao.androidboilerplate.dao.entity.GithubRepo
import com.example.takao.androidboilerplate.databinding.ItemRepositoryBinding
import java.lang.IllegalStateException

@EpoxyModelClass(layout = R.layout.item_repository)
abstract class GithubRepositoryModel : EpoxyModelWithHolder<GithubRepositoryModel.RepositoryViewHolder>() {

    @EpoxyAttribute
    lateinit var repo: GithubRepo

    override fun bind(holder: RepositoryViewHolder) {
        super.bind(holder)
        holder.binding.repo = repo
    }

    inner class RepositoryViewHolder : EpoxyHolder() {
        lateinit var binding: ItemRepositoryBinding

        override fun bindView(itemView: View) {
            this.binding = ItemRepositoryBinding.bind(itemView) ?: run {
                throw IllegalStateException("Cannot create binding.")
            }
        }
    }
}
