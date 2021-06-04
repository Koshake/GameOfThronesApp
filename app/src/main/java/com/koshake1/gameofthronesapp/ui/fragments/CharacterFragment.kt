package com.koshake1.gameofthronesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.koshake1.gameofthronesapp.App
import com.koshake1.gameofthronesapp.R
import com.koshake1.gameofthronesapp.di.character.CharacterSubComponent
import com.koshake1.gameofthronesapp.mvp.model.entity.HousesData
import com.koshake1.gameofthronesapp.mvp.presenter.CharactersPresenter
import com.koshake1.gameofthronesapp.mvp.view.ICharacterView
import com.koshake1.gameofthronesapp.ui.BackButtonListener
import com.koshake1.gameofthronesapp.ui.fragments.adapter.CharactersAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_characters.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CharacterFragment : MvpAppCompatFragment(), ICharacterView, BackButtonListener {

    companion object {
        private const val HOUSE_ARG = "house"
        private val TAG = "tag"
        fun newInstance(house: HousesData) = CharacterFragment().apply {
            arguments = Bundle().apply {
                putParcelable(HOUSE_ARG, house)
            }
        }
    }

    private var adapter: CharactersAdapter? = null
    private var characterSubComponent: CharacterSubComponent? = null

    val presenter: CharactersPresenter by moxyPresenter {
        val houses = arguments?.getParcelable<HousesData>(
            HOUSE_ARG
        ) as HousesData
        characterSubComponent = App.instance.initCharacterSubComponent()
        CharactersPresenter(houses).apply { characterSubComponent?.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_characters, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)?.setSupportActionBar(charactersToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_character, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_back -> presenter.backPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun init() {
        Log.d(TAG, "init character")
        charactersRecycler.layoutManager = LinearLayoutManager(context)
        adapter = CharactersAdapter(presenter.charactersListPresenter)
        charactersRecycler.adapter = adapter
    }

    override fun updateList() {
        Log.d(TAG, "update list character")
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        characterSubComponent = null
        App.instance.releaseCharacterSubComponent()
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}