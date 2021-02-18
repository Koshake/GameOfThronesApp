package com.koshake1.gameofthronesapp.ui.fragments.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.koshake1.gameofthronesapp.R
import com.koshake1.gameofthronesapp.mvp.model.image.IImageLoader
import com.koshake1.gameofthronesapp.mvp.presenter.list.IHouseListPresenter
import com.koshake1.gameofthronesapp.mvp.view.list.HouseItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_house.view.*

class HousesAdapter(val presenter : IHouseListPresenter) : RecyclerView.Adapter<HousesAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, HouseItemView {
        override var pos = -1

        override fun setName(text: String) = with (containerView) {
            houseTextView.text = text
        }

        override fun loadEmblem(url: String) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_house, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }
}