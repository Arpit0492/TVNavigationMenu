package com.androijo.tvnavigation.interfaces

interface NavigationStateListener {
    fun onStateChanged(expanded: Boolean, lastSelected: String?)
}