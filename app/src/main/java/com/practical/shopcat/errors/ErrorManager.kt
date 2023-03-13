package com.practical.shopcat.errors

import com.practical.shopcat.error.mapper.ErrorMapper
import com.practical.shopcat.error.Error
import javax.inject.Inject


class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
