package com.practical.shopcat.data.service

import com.practical.shopcat.model.ShopItem
import retrofit2.Response
import retrofit2.http.GET

interface ShopByTypeService {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<ShopItem>>
}
