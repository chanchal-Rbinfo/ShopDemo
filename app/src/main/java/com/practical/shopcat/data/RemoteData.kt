package com.practical.shopcat.data

import com.practical.shopcat.data.service.ShopByTypeService
import com.practical.shopcat.error.NETWORK_ERROR
import com.practical.shopcat.error.NO_INTERNET_CONNECTION
import com.practical.shopcat.model.ShopByType
import com.practical.shopcat.model.ShopItem
import com.practical.shopcat.utils.NetworkConnectivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) : RemoteDataSource {

    override suspend fun requestShopByCat(): Resource<ShopByType> {
        val recipesService = serviceGenerator.createService(ShopByTypeService::class.java)
        return when (val response = processCall(recipesService::fetchRecipes)) {
            is List<*> -> {
                Resource.Success(data = ShopByType(response as ArrayList<ShopItem>))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}