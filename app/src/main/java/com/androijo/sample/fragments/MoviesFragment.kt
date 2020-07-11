package com.androijo.sample.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.leanback.widget.Row
import com.androijo.sample.interfaces.NavigationMenuCallback


class MoviesFragment : RowsSupportFragment() {

    private var mRowsAdapter: ArrayObjectAdapter = ArrayObjectAdapter(RowPresenterSelector())

    private lateinit var navigationMenuCallback: NavigationMenuCallback
    private val dataList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataList.add("1")
        dataList.add("2")
        dataList.add("3")
        dataList.add("4")
        dataList.add("5")

        createRows()

        onItemViewSelectedListener = OnItemViewSelectedListener {itemViewHolder, item, rowViewHolder, row ->
            val indexOfItem = ((row as CardListRow).adapter as ArrayObjectAdapter).indexOf(item)

            itemViewHolder.view.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN) {
                    when (keyCode) {
                        KeyEvent.KEYCODE_DPAD_LEFT -> {
                            if (indexOfItem == 0) {
                                navigationMenuCallback.navMenuToggle(true)
                            }
                        }
                    }
                }
                false
            }
        }
    }

    private fun createRows() {
        for(rowIndex in 1..5){
            mRowsAdapter.add(createNewRow(rowIndex))
        }
    }

    private fun createNewRow(rowIndex: Int): Row {
        val presenterSelector = activity?.baseContext?.let {
            CardPresenterSelector(it)
        }
        val adapter = ArrayObjectAdapter(presenterSelector)
        for (data in dataList) {
            adapter.add(data)
        }
        val headerItem = HeaderItem("Row $rowIndex")
        return CardListRow(headerItem, adapter)
    }


    fun setNavigationMenuCallback(callback: NavigationMenuCallback) {
        this.navigationMenuCallback = callback
    }


}
