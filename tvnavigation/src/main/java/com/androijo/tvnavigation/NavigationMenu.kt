package com.androijo.tvnavigation

/**
 * @author Arpit Johri
 * @createdOn Saturday, 11th July, 2020
 *
 */

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.androijo.tvnavigation.interfaces.FragmentChangeListener
import com.androijo.tvnavigation.interfaces.NavigationStateListener
import kotlinx.android.synthetic.main.fragment_nav_menu.*

class NavigationMenu : Fragment() {


    private lateinit var fragmentChangeListener: FragmentChangeListener
    private lateinit var navigationToHostListener: NavigationStateListener

    private var TAG_CLASS_NAME = NavigationMenu::class.java.toString()
    private val movies = activity?.getString(R.string.Movies)
    private val shows = activity?.getString(R.string.Shows)
    private val news = activity?.getString(R.string.News)
    private val music = activity?.getString(R.string.Music)
    private val podcasts = activity?.getString(R.string.PodCasts)
    private val settings = activity?.getString(R.string.Settings)
    private var lastSelectedMenu: String? = movies
    private var moviesAllowedToGainFocus = false
    private var settingsAllowedToGainFocus = false
    private var musicAllowedToGainFocus = false
    private var podcastsAllowedToGainFocus = false
    private var showsAllowedToGainFocus = true
    private var newsAllowedToGainFocus = false
    private var switchUserAllowedToGainFocus = false
    private var menuTextAnimationDelay = 0//200

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_nav_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //by default selection
        setMenuIconFocusView(R.drawable.ic_movie_selected, movies_IB, true)

        //Navigation Menu Options Focus, Key Listeners
        podcastsListeners()

        moviesListeners()

        showsListeners()

        musicListeners()

        settingsListeners()

        newsListeners()

    }

    private fun moviesListeners() {

        Log.i(
            TAG_CLASS_NAME, "searchAllowedToGainFocus = $moviesAllowedToGainFocus" +
                    "is search this last selected = ${lastSelectedMenu == movies}" +
                    "is search nav open = ${isNavigationOpen()}"
        )

        movies_IB.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (isNavigationOpen()) {
                    setFocusedView(movies_IB, R.drawable.ic_movie_selected)
                    setMenuNameFocusView(movies_TV, true)
//                    focusIn(binding.searchIB, 0)
                }
            } else {
                if (isNavigationOpen()) {
                    setOutOfFocusedView(movies_IB, R.drawable.ic_movie_unselected)
                    setMenuNameFocusView(movies_TV, false)
//                    focusOut(binding.searchIB, 0)
                }
            }
        }

        movies_IB.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {//only when key is pressed down
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
                        closeNav()
                        navigationToHostListener.onStateChanged(false, lastSelectedMenu)
                    }
                    KeyEvent.KEYCODE_ENTER -> {
//                        lastSelectedMenu = NAV_NAME_SEARCH
                        fragmentChangeListener.switchFragment(movies)
                        closeNav()
                    }
                    KeyEvent.KEYCODE_DPAD_UP -> {
                        if (!movies_IB.isFocusable)
                            movies_IB.isFocusable = true
                        switchUserAllowedToGainFocus = true
                    }
                    KeyEvent.KEYCODE_DPAD_CENTER -> {
//                        lastSelectedMenu = NAV_NAME_SEARCH
                        fragmentChangeListener.switchFragment(movies)
                        closeNav()
                    }
                }
            }
            false
        }
    }

    private fun showsListeners() {

        Log.i(
            TAG_CLASS_NAME, "learnAllowedToGainFocus = $showsAllowedToGainFocus" +
                    "is learn this last selected = ${lastSelectedMenu == shows}" +
                    "is learn nav open = ${isNavigationOpen()}"
        )

        shows_IB.setOnFocusChangeListener { v, hasFocus ->

            if (hasFocus) {
                if (isNavigationOpen()) {
                    setFocusedView(shows_IB, R.drawable.ic_shows_selected)
                    setMenuNameFocusView(shows_TV, true)
//                    focusIn(binding.learnIB, 0)
                }
            } else {
                if (isNavigationOpen()) {
                    setOutOfFocusedView(shows_IB, R.drawable.ic_shows_unselected)
                    setMenuNameFocusView(shows_TV, false)
//                    focusOut(binding.learnIB, 0)
                }
            }
        }

        shows_IB.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {//only when key is pressed down
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
                        closeNav()
                        navigationToHostListener.onStateChanged(false, lastSelectedMenu)
                    }
                    KeyEvent.KEYCODE_ENTER -> {
                        lastSelectedMenu = shows
                        fragmentChangeListener.switchFragment(shows)
                        closeNav()
                    }
                    KeyEvent.KEYCODE_DPAD_UP -> {
                        if (!shows_IB.isFocusable)
                            shows_IB.isFocusable = true
                        switchUserAllowedToGainFocus = true
                    }
                    KeyEvent.KEYCODE_DPAD_CENTER -> {
                        lastSelectedMenu = shows
                        fragmentChangeListener.switchFragment(shows)
                        closeNav()
                    }
                }
            }
            false
        }

    }

    private fun podcastsListeners() {

        Log.i(
            TAG_CLASS_NAME, "practiceAllowedToGainFocus = $podcastsAllowedToGainFocus, " +
                    "is practice this last selected = ${lastSelectedMenu == podcasts}" +
                    "is practice nav open = ${isNavigationOpen()}"
        )

        podcasts_IB.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (isNavigationOpen()) {
                    setFocusedView(podcasts_IB, R.drawable.ic_podcast_selected)
                    setMenuNameFocusView(podcasts_TV, true)
//                    focusIn(binding.practiceIB, 0)
                }

            } else {
                if (isNavigationOpen()) {
                    setOutOfFocusedView(podcasts_IB, R.drawable.ic_podcast_unselected)
                    setMenuNameFocusView(podcasts_TV, false)
//                    focusOut(binding.practiceIB, 0)
                }
            }


            // Redraw, make the drawing order adjustment of items take effect
            val parent = v.parent as ViewGroup
            parent.requestLayout()
            parent.postInvalidate()
        }

        podcasts_IB.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {//only when key is pressed down
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
                        closeNav()
                        navigationToHostListener.onStateChanged(false, lastSelectedMenu)
                    }
                    KeyEvent.KEYCODE_ENTER -> {
                        lastSelectedMenu = podcasts
                        fragmentChangeListener.switchFragment(podcasts)
                        closeNav()
                    }
                    KeyEvent.KEYCODE_DPAD_CENTER -> {
                        lastSelectedMenu = podcasts
                        fragmentChangeListener.switchFragment(podcasts)
                        closeNav()
                    }
                }
            }
            false
        }
    }

    private fun musicListeners() {
        Log.i(
            TAG_CLASS_NAME, "achieveAllowedToGainFocus = $settingsAllowedToGainFocus, " +
                    "is achieve this last selected = ${lastSelectedMenu == music}" +
                    "is achieve nav open = ${isNavigationOpen()}"
        )

        music_IB.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (isNavigationOpen()) {
                    setFocusedView(music_IB, R.drawable.ic_music_selected)
                    setMenuNameFocusView(music_TV, true)
//                    focusIn(binding.practiceIB, 0)
                }

            } else {
                if (isNavigationOpen()) {
                    setOutOfFocusedView(music_IB, R.drawable.ic_music_selected)
                    setMenuNameFocusView(music_TV, false)
//                    focusOut(binding.practiceIB, 0)
                }
            }


            // Redraw, make the drawing order adjustment of items take effect
            val parent = v.parent as ViewGroup
            parent.requestLayout()
            parent.postInvalidate()
        }

        music_IB.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {//only when key is pressed down
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
                        closeNav()
                        navigationToHostListener.onStateChanged(false, lastSelectedMenu)
                    }
                    KeyEvent.KEYCODE_ENTER -> {
                        lastSelectedMenu = music
                        fragmentChangeListener.switchFragment(music)
                        closeNav()
                    }
                    KeyEvent.KEYCODE_DPAD_CENTER -> {
                        lastSelectedMenu = music
                        fragmentChangeListener.switchFragment(music)
                        closeNav()
                    }
                }
            }
            false
        }

    }

    private fun settingsListeners() {

        settings_IB.setOnFocusChangeListener { v, hasFocus ->
            Log.i(
                TAG_CLASS_NAME, "profileAllowedToGainFocus = $musicAllowedToGainFocus" +
                        "is profile this last selected = ${lastSelectedMenu == settings}" +
                        "is profile nav open = ${isNavigationOpen()}"
            )

            if (hasFocus) {
                if (isNavigationOpen()) {
                    setFocusedView(settings_IB, R.drawable.ic_settings_selected)
                    setMenuNameFocusView(settings_TV, true)
//                    focusIn(binding.profileIB, 0)
                }
            } else {
                if (isNavigationOpen()) {
                    setOutOfFocusedView(settings_IB, R.drawable.ic_settings_unselected)
                    setMenuNameFocusView(settings_TV, false)
//                    focusOut(binding.profileIB, 0)
                }
            }
        }

        settings_IB.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {//only when key is pressed down
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
                        closeNav()
                        navigationToHostListener.onStateChanged(false, lastSelectedMenu)
                    }
                    KeyEvent.KEYCODE_ENTER -> {
                        lastSelectedMenu = settings
                        fragmentChangeListener.switchFragment(settings)
                        closeNav()
                    }
                    KeyEvent.KEYCODE_DPAD_CENTER -> {
                        lastSelectedMenu = settings
                        fragmentChangeListener.switchFragment(settings)
                        closeNav()
                    }
                }
            }
            false
        }
    }

    private fun newsListeners() {

        news_IB.setOnFocusChangeListener { v, hasFocus ->

            if (hasFocus) {
                if (isNavigationOpen()) {
                    setFocusedView(news_IB, R.drawable.ic_news_selected)
                    setMenuNameFocusView(news_TV, true)
//                    focusIn(binding.profileIB, 0)
                }
            } else {
                if (isNavigationOpen()) {
                    setOutOfFocusedView(news_IB, R.drawable.ic_news_unselected)
                    setMenuNameFocusView(news_TV, false)
//                    focusOut(binding.profileIB, 0)
                }
            }
        }

        news_IB.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {//only when key is pressed down
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
                        closeNav()
                        navigationToHostListener.onStateChanged(false, lastSelectedMenu)
                    }
                    KeyEvent.KEYCODE_ENTER -> {
                        lastSelectedMenu = news
                        fragmentChangeListener.switchFragment(news)
                        closeNav()
                    }
                    KeyEvent.KEYCODE_DPAD_CENTER -> {
                        lastSelectedMenu = news
                        fragmentChangeListener.switchFragment(news)
                        closeNav()
                    }
                }
            }
            false
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setOutOfFocusedView(view: ImageButton, resource: Int) {
        setMenuIconFocusView(resource, view, false)
    }

    private fun setFocusedView(view: ImageButton, resource: Int) {
        setMenuIconFocusView(resource, view, true)
    }


    /**
     * Setting animation when focus is lost
     */
    fun focusOut(v: View, position: Int) {
        val scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.05f, 1.0f)
        val scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.05f, 1.0f)
        val set = AnimatorSet()
        set.play(scaleX).with(scaleY)
        set.start()
    }

    /**
     * Setting the animation when getting focus
     */
    fun focusIn(v: View, position: Int) {
        val scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.05f)
        val scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.05f)
        val set = AnimatorSet()
        set.play(scaleX).with(scaleY)
        set.start()
    }

    private fun setMenuIconFocusView(resource: Int, view: ImageButton, inFocus: Boolean) {
        view.setImageResource(resource)
    }

    private fun setMenuNameFocusView(view: TextView, inFocus: Boolean) {
        if (inFocus) {
            view.setTextColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.navigation_menu_focus_color
                )
            )
        } else
            view.setTextColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.navigation_menu_focus_out_color
                )
            )
    }

    fun openNav() {
        enableNavMenuViews(View.VISIBLE)
        val lp = FrameLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
        open_nav_CL.layoutParams = lp
        navigationToHostListener.onStateChanged(true, lastSelectedMenu)

        when (lastSelectedMenu) {

            shows -> {
                shows_IB.requestFocus()
                showsAllowedToGainFocus = true
                setMenuNameFocusView(shows_TV, true)
            }
            movies -> {
                movies_IB.requestFocus()
                moviesAllowedToGainFocus = true
                setMenuNameFocusView(movies_TV, true)
            }
            podcasts -> {
                podcasts_IB.requestFocus()
                podcastsAllowedToGainFocus = true
                setMenuNameFocusView(podcasts_TV, true)
            }
            music -> {
                music_IB.requestFocus()
                musicAllowedToGainFocus = true
                setMenuNameFocusView(music_TV, true)
            }
            news -> {
                news_IB.requestFocus()
                newsAllowedToGainFocus = true
                setMenuNameFocusView(news_TV, true)
            }
            settings -> {
                settings_IB.requestFocus()
                settingsAllowedToGainFocus = true
                setMenuNameFocusView(settings_TV, true)
            }

        }

    }

    fun closeNav() {
        enableNavMenuViews(View.GONE)
        val lp = FrameLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
        open_nav_CL.layoutParams = lp

        //highlighting last selected menu icon
        highlightMenuSelection(lastSelectedMenu)

        //Setting out of focus views for menu icons, names
        unHighlightMenuSelections(lastSelectedMenu)

    }

    private fun unHighlightMenuSelections(lastSelectedMenu: String?) {
        if (!lastSelectedMenu.equals(movies, true)) {
            setOutOfFocusedView(movies_IB, R.drawable.ic_movie_unselected)
            setMenuNameFocusView(movies_TV, false)
        }
        if (!lastSelectedMenu.equals(shows, true)) {
            setOutOfFocusedView(shows_IB, R.drawable.ic_shows_unselected)
            setMenuNameFocusView(shows_TV, false)
        }
        if (!lastSelectedMenu.equals(podcasts, true)) {
            setOutOfFocusedView(podcasts_IB, R.drawable.ic_podcast_unselected)
            setMenuNameFocusView(podcasts_TV, false)
        }
        if (!lastSelectedMenu.equals(music, true)) {
            setOutOfFocusedView(music_IB, R.drawable.ic_music_unselected)
            setMenuNameFocusView(music_TV, false)
        }
        if (!lastSelectedMenu.equals(news, true)) {
            setOutOfFocusedView(news_IB, R.drawable.ic_news_unselected)
            setMenuNameFocusView(news_TV, false)
        }
        if (!lastSelectedMenu.equals(settings, true)) {
            setOutOfFocusedView(settings_IB, R.drawable.ic_settings_unselected)
            setMenuNameFocusView(settings_TV, false)
        }
    }

    private fun highlightMenuSelection(lastSelectedMenu: String?) {
        when (lastSelectedMenu) {
            movies -> {
                setFocusedView(movies_IB, R.drawable.ic_movie_selected)
            }
            shows -> {
                setFocusedView(shows_IB, R.drawable.ic_shows_selected)
            }
            podcasts -> {
                setFocusedView(podcasts_IB, R.drawable.ic_podcast_selected)
            }
            music -> {
                setFocusedView(music_IB, R.drawable.ic_music_selected)
            }
            news -> {
                setFocusedView(news_IB, R.drawable.ic_news_selected)
            }
            settings -> {
                setFocusedView(settings_IB, R.drawable.ic_settings_selected)
            }
        }
    }

    private fun enableNavMenuViews(visibility: Int) {

        if (visibility == View.GONE) {
            menuTextAnimationDelay = 0//200 //reset
            movies_IB.visibility = visibility
            shows_IB.visibility = visibility
            news_IB.visibility = visibility
            music_IB.visibility = visibility
            podcasts_IB.visibility = visibility
            settings_IB.visibility = visibility
        } else {
            animateMenuNamesEntry(movies_IB, visibility, 1)
        }

    }

    private fun animateMenuNamesEntry(view: View, visibility: Int, viewCode: Int) {
        view.postDelayed({
            view.visibility = visibility
            val animate = AnimationUtils.loadAnimation(context, R.anim.slide_in_left_menu_name)
            view.startAnimation(animate)
            menuTextAnimationDelay = 0//50
            when (viewCode) {
                1 -> {
                    animateMenuNamesEntry(shows_TV, visibility, viewCode + 1)
                }
                2 -> {
                    animateMenuNamesEntry(podcasts_TV, visibility, viewCode + 1)
                }
                3 -> {
                    animateMenuNamesEntry(music_TV, visibility, viewCode + 1)
                }
                4 -> {
                    animateMenuNamesEntry(news_TV, visibility, viewCode + 1)
                }
                5 -> {
                    animateMenuNamesEntry(settings_TV, visibility, viewCode + 1)
                }
            }
        }, menuTextAnimationDelay.toLong())
    }

    fun isNavigationOpen() = movies_IB.visibility == View.VISIBLE

    fun setFragmentChangeListener(callback: FragmentChangeListener) {
        this.fragmentChangeListener = callback
    }

    fun setNavigationStateListener(callback: NavigationStateListener) {
        this.navigationToHostListener = callback
    }

    fun setSelectedMenu(navMenuName: String) {
        when (navMenuName) {
            shows -> {
                lastSelectedMenu = shows
            }
            movies -> {
                lastSelectedMenu = movies
            }
        }

        highlightMenuSelection(lastSelectedMenu)
        unHighlightMenuSelections(lastSelectedMenu)

    }


}
