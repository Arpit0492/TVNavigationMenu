package com.androijo.sample.activities

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.androijo.sample.R
import com.androijo.sample.interfaces.NavigationMenuCallback
import com.androijo.sample.fragments.*
import com.androijo.tvnavigation.NavigationMenu
import com.androijo.tvnavigation.interfaces.FragmentChangeListener
import com.androijo.tvnavigation.interfaces.NavigationStateListener
import com.androijo.tvnavigation.utils.Constants
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : FragmentActivity(), NavigationStateListener, FragmentChangeListener,
    NavigationMenuCallback {

    private lateinit var navMenuFragment: NavigationMenu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        navMenuFragment = NavigationMenu()
        fragmentReplacer(nav_fragment.id, navMenuFragment)
        fragmentReplacer(main_FL.id, MoviesFragment())
    }

    private fun fragmentReplacer(containerId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(containerId, fragment).commit()
    }

    /**
     * communication from left-side navigation to right-side content
     */
    override fun onStateChanged(expanded: Boolean, lastSelected: String?) {

    }

    override fun switchFragment(fragmentName: String?) {
        when (fragmentName) {
            Constants.nav_menu_movies -> {
                fragmentReplacer(main_FL.id, MoviesFragment())
            }
            Constants.nav_menu_news -> {
                fragmentReplacer(main_FL.id, NewsFragment())
            }
            Constants.nav_menu_music -> {
                fragmentReplacer(main_FL.id, MusicFragment())
            }
            Constants.nav_menu_shows -> {
                fragmentReplacer(main_FL.id, ShowsFragment())
            }
            Constants.nav_menu_settings -> {
                fragmentReplacer(main_FL.id, SettingsFragment())
            }
            Constants.nav_menu_podcasts -> {
                fragmentReplacer(main_FL.id, PodcastsFragment())
            }
        }
    }

    override fun navMenuToggle(toShow: Boolean) {

        try {
            if (toShow) {
                main_FL.clearFocus()
                nav_fragment.requestFocus()
                navEnterAnimation()
                navMenuFragment.openNav()
            } else {
                nav_fragment.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.fastlane_background)
                )
                nav_fragment.clearFocus()
                main_FL.requestFocus()
                navMenuFragment.closeNav()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAttachFragment(fragment: Fragment) {

        when(fragment) {
            is MoviesFragment -> {
                fragment.setNavigationMenuCallback(this)
            }
            is NewsFragment -> {
                fragment.setNavigationMenuCallback(this)
            }
            is MusicFragment -> {
                fragment.setNavigationMenuCallback(this)
            }
            is SettingsFragment -> {
                fragment.setNavigationMenuCallback(this)
            }
            is PodcastsFragment -> {
                fragment.setNavigationMenuCallback(this)
            }
            is ShowsFragment -> {
                fragment.setNavigationMenuCallback(this)
            }
            is NavigationMenu -> {
                fragment.setFragmentChangeListener(this)
                fragment.setNavigationStateListener(this)
            }
        }

    }

    private fun navEnterAnimation() {
        val animate = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        nav_fragment.startAnimation(animate)
    }
}
