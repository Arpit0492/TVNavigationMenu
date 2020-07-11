package com.androijo.tvnavigation

interface NavigationStateListener {
    fun onStateChanged(expanded: Boolean, lastSelected: String)
}