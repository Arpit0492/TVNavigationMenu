package com.androijo.sample.fragments



import android.content.Context
import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.Presenter

/**
 * This abstract, generic class will create and manage the
 * ViewHolder and will provide typed Presenter callbacks such that you do not have to perform casts
 * on your own.
 *
 * @param <T> View type for the card.
</T> */
abstract class AbstractCardPresenter<T : BaseCardView?>
/**
 * @param context The current context.
 */(val context: Context) :
    Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = onCreateView()
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        onBindViewHolder(item, viewHolder.view as T)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        onUnbindViewHolder(viewHolder.view as T)
    }

    fun onUnbindViewHolder(cardView: T) {
        // Nothing to clean up. Override if necessary.
    }

    /**
     * Invoked when a new view is created.
     *
     * @return Returns the newly created view.
     */
    protected abstract fun onCreateView(): T

    abstract fun onBindViewHolder(card: Any, cardView: T)

    companion object {
        private const val TAG = "AbstractCardPresenter"
    }


}