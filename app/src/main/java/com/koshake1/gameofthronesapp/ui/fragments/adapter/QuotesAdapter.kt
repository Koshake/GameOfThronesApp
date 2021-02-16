package com.koshake1.gameofthronesapp.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koshake1.gameofthronesapp.R
import com.koshake1.gameofthronesapp.mvp.presenter.CharactersPresenter
import com.koshake1.gameofthronesapp.mvp.presenter.QuotesPresenter
import com.koshake1.gameofthronesapp.mvp.view.list.HouseItemView
import com.koshake1.gameofthronesapp.mvp.view.list.QuotesItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_house.view.*
import kotlinx.android.synthetic.main.item_quote.view.*

class QuotesAdapter(val presenter : QuotesPresenter.QuotesListPresenter) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, QuotesItemView {
        override var pos = -1

        override fun setQuote(text: String) = with (containerView) {
            quoteTextView.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }
}