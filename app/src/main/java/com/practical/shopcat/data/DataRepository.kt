package com.practical.shopcat.data

import com.practical.shopcat.model.ShopByType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val ioDispatcher: CoroutineContext) : DataRepositorySource {

    override suspend fun requestShopByCat(): Flow<Resource<ShopByType>> {
        return flow {
            emit(remoteRepository.requestShopByCat())
        }.flowOn(ioDispatcher)
    }

}
