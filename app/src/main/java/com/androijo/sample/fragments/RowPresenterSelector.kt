package com.androijo.sample.fragments

import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.PresenterSelector

class RowPresenterSelector : PresenterSelector() {

    val customListRowPresenter = object : ListRowPresenter(FocusHighlight.ZOOM_FACTOR_LARGE, false) {
        override fun isUsingDefaultListSelectEffect() = false
        override fun isUsingDefaultShadow(): Boolean {
            return false
        }

    }.apply {
        shadowEnabled = false
    }


    private val mShadowEnabledRowPresenter = customListRowPresenter
    private val mShadowDisabledRowPresenter = customListRowPresenter

    init {
        mShadowEnabledRowPresenter.setNumRows(1)
        mShadowDisabledRowPresenter.shadowEnabled = true
    }

    override fun getPresenter(item: Any): Presenter {

        if (item !is CardListRow) return mShadowDisabledRowPresenter
        return mShadowDisabledRowPresenter
    }

    override fun getPresenters(): Array<Presenter> {
        return arrayOf(mShadowDisabledRowPresenter, mShadowEnabledRowPresenter)
    }

    fun getListRowPresenter(): ListRowPresenter = customListRowPresenter

}
