package com.practical.shopcat.data

import com.practical.shopcat.model.ShopByType


internal interface RemoteDataSource {
    suspend fun requestShopByCat(): Resource<ShopByType>
}
