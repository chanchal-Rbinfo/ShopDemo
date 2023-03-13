package com.practical.shopcat.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.practical.shopcat.R
import com.practical.shopcat.databinding.ShopItemBinding
import com.squareup.picasso.Picasso
import com.practical.shopcat.listeners.RecyclerItemListener
import com.practical.shopcat.model.ShopItem


class RecipeViewHolder(private val itemBinding: ShopItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipesItem: ShopItem, recyclerItemListener: RecyclerItemListener) {
//        itemBinding.tvCaption.text = recipesItem.description
//        itemBinding.tvName.text = recipesItem.name
//        Picasso.get().load(recipesItem.thumb).placeholder(R.drawable.ic_healthy_food).error(R.drawable.ic_healthy_food).into(itemBinding.ivRecipeItemImage)
//        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(recipesItem) }
    }
}

