package com.practical.shopcat.data

import com.practical.shopcat.model.ShopByType
import kotlinx.coroutines.flow.Flow


interface DataRepositorySource {
    suspend fun requestShopByCat(): Flow<Resource<ShopByType>>
}
