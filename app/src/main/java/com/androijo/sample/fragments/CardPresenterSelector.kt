package com.androijo.sample.fragments

import android.content.Context
import android.util.Log
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector

class CardPresenterSelector(private val mContext: Context) : PresenterSelector() {

    override fun getPresenter(item: Any?): Presenter? {
        return CardPresenter(mContext)
    }

    override fun getPresenters(): Array<Presenter> {
        return super.getPresenters()
    }

}







