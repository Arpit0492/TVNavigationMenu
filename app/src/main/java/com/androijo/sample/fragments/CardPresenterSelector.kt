package com.androijo.sample.fragments

import android.content.Context
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector

/**
 * This PresenterSelector will decide what Presenter to use depending on a given card's type.
 */

class CardPresenterSelector(private val mContext: Context) : PresenterSelector() {


    override fun getPresenter(item: Any?): Presenter? {
        return CardPresenter(mContext)
    }

}







