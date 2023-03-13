package com.practical.shopcat.listeners

import com.practical.shopcat.model.ShopItem

interface RecyclerItemListener {
    fun onItemSelected(recipe : ShopItem)
}
