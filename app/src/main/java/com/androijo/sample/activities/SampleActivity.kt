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

    private lateinit var moviesFragment: MoviesFragment
    private lateinit var showsFragment: ShowsFragment
    private lateinit var musicFragment: MusicFragment
    private lateinit var podcastsFragment: PodcastsFragment
    private lateinit var newsFragment: NewsFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var navMenuFragment: NavigationMenu
    private var currentSelectedFragment = Constants.nav_menu_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        navMenuFragment = NavigationMenu()
        fragmentReplacer(nav_fragment.id, navMenuFragment)
        moviesFragment = MoviesFragment()
        fragmentReplacer(main_FL.id, moviesFragment)
    }

    private fun fragmentReplacer(containerId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(containerId, fragment).commit()
    }

    /**
     * communication from left-side navigation to right-side content
     */
    override fun onStateChanged(expanded: Boolean, lastSelected: String?) {
        if (!expanded) {
            nav_fragment.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.yellow
                )
            )
            nav_fragment.clearFocus()

            when (currentSelectedFragment) {
                Constants.nav_menu_movies -> {
                    currentSelectedFragment = Constants.nav_menu_movies
                    moviesFragment.restoreSelection()
                }
                Constants.nav_menu_music -> {
                    currentSelectedFragment = Constants.nav_menu_music
                    musicFragment.selectFirstItem()
                }
                Constants.nav_menu_news -> {
                    currentSelectedFragment = Constants.nav_menu_news
                    newsFragment.selectFirstItem()
                }
                Constants.nav_menu_podcasts -> {
                    currentSelectedFragment = Constants.nav_menu_podcasts
                    podcastsFragment.selectFirstItem()
                }
                Constants.nav_menu_settings -> {
                    currentSelectedFragment = Constants.nav_menu_settings
                    settingsFragment.selectFirstItem()
                }
            }
        } else {
            //do
        }
    }

    override fun switchFragment(fragmentName: String?) {
        when (fragmentName) {
            Constants.nav_menu_movies -> {
                moviesFragment = MoviesFragment()
                fragmentReplacer(main_FL.id, MoviesFragment())
                moviesFragment.restoreSelection()
            }
            Constants.nav_menu_news -> {
                newsFragment = NewsFragment()
                fragmentReplacer(main_FL.id, newsFragment)
                newsFragment.selectFirstItem()
            }
            Constants.nav_menu_music -> {
                musicFragment = MusicFragment()
                fragmentReplacer(main_FL.id, musicFragment)
                musicFragment.selectFirstItem()
            }
            Constants.nav_menu_shows -> {
                showsFragment = ShowsFragment()
                fragmentReplacer(main_FL.id, showsFragment)
                showsFragment.selectFirstItem()
            }
            Constants.nav_menu_settings -> {
                settingsFragment = SettingsFragment()
                fragmentReplacer(main_FL.id, settingsFragment)
                settingsFragment.selectFirstItem()
            }
            Constants.nav_menu_podcasts -> {
                podcastsFragment = PodcastsFragment()
                fragmentReplacer(main_FL.id, podcastsFragment)
                podcastsFragment.selectFirstItem()
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
                    ContextCompat.getColor(this, R.color.yellow)
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
