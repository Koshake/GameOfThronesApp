package com.koshake1.gameofthronesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.koshake1.gameofthronesapp.App
import com.koshake1.gameofthronesapp.R
import com.koshake1.gameofthronesapp.mvp.presenter.SingleQuotePresenter
import com.koshake1.gameofthronesapp.mvp.view.ISingleQuoteView
import com.koshake1.gameofthronesapp.ui.BackButtonListener
import com.koshake1.gameofthronesapp.ui.activity.MainActivity
import kotlinx.android.synthetic.main.appbar.*
import kotlinx.android.synthetic.main.appbar.collapsing_toolbar
import kotlinx.android.synthetic.main.fragment_single_quote.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SingleQuoteFragment : MvpAppCompatFragment(), ISingleQuoteView, BackButtonListener, NavigationView.OnNavigationItemSelectedListener {

    companion object {
        private const val NAME_ARG = "name"
        private const val QUOTE_ARG = "quote"
        private const val TAG = "tag"

        fun newInstance(name: String, quote: String) = SingleQuoteFragment().apply {
            arguments = Bundle().apply {
                putString(NAME_ARG, name)
                putString(QUOTE_ARG, quote)
            }
        }
    }

    val presenter: SingleQuotePresenter by moxyPresenter {
        val name = arguments?.getString(NAME_ARG) as String
        val quote = arguments?.getString(QUOTE_ARG) as String
        SingleQuotePresenter(name, quote).apply { App.instance.appComponent.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? =
        View.inflate(context, R.layout.fragment_single_quote, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
        initDrawer()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_houses -> return true
            R.id.nav_fav -> return true
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> presenter.backPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateText(text: String) {
        Log.d(TAG, "single quote $text")
        textViewQuote.text = context?.getString(R.string.single_quote_text, text)
    }

    override fun updateName(text: String) {
        Log.d(TAG, "single quote $text")
        collapsing_toolbar.title = "$text:"
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return  true
    }

    override fun initDrawer() {
        Log.d(TAG, "init drawer ")
    }

    private fun initToolBar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        (requireActivity() as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }
}