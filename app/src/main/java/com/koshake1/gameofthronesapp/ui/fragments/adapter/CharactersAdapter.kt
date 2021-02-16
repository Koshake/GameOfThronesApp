package com.koshake1.gameofthronesapp.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koshake1.gameofthronesapp.R
import com.koshake1.gameofthronesapp.mvp.presenter.CharactersPresenter
import com.koshake1.gameofthronesapp.mvp.view.list.CharacterItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersAdapter(val presenter : CharactersPresenter.CharactersListPresenter) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer, CharacterItemView {

        override fun setName(text: String) = with(containerView) { nameTextView.text = text }
        override fun setSlug(text: String) = with(containerView) { slugTextView.text = text }
        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))

    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()
}