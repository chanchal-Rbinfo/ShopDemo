package com.practical.shopcat.errors

import com.practical.shopcat.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
