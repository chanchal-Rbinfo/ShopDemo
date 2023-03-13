package com.practical.shopcat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practical.shopcat.MainActivityViewModel
import com.practical.shopcat.databinding.ShopItemBinding
import com.practical.shopcat.listeners.RecyclerItemListener
import com.practical.shopcat.model.ShopItem

/**
 * Created by AhmedEltaher
 */

class ShopsAdapter(private val mainActivityViewModel: MainActivityViewModel, private val shops: List<ShopItem>) : RecyclerView.Adapter<RecipeViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(shopItem: ShopItem) {
            mainActivityViewModel.openRecipeDetails(shopItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = ShopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(shops[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return shops.size
    }
}

