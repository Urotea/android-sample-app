package com.example.takao.androidboilerplate.ui.third.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.takao.androidboilerplate.R
import com.example.takao.androidboilerplate.dao.entity.GithubOwner
import com.example.takao.androidboilerplate.databinding.ItemOwnerBinding

@EpoxyModelClass(layout = R.layout.item_owner)
abstract class GithubOwnerModel : EpoxyModelWithHolder<GithubOwnerModel.OwnerViewHolder>() {

    @EpoxyAttribute
    lateinit var owner: GithubOwner

    @EpoxyAttribute
    lateinit var cardClickListener: (String) -> Unit

    override fun bind(holder: OwnerViewHolder) {
        super.bind(holder)
        holder.binding.owner = owner
        holder.binding.containerCard.setOnClickListener{
            cardClickListener(owner.name)
        }
    }

    inner class OwnerViewHolder : EpoxyHolder() {
        lateinit var binding: ItemOwnerBinding

        override fun bindView(itemView: View) {
            this.binding = ItemOwnerBinding.bind(itemView) ?: run {
                throw IllegalStateException("Cannot create binding.")
            }
        }
    }
}