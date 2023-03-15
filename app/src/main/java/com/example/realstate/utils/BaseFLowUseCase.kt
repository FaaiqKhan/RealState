package com.example.realstate.utils

import kotlinx.coroutines.flow.Flow

interface FLowUseCase<in P, R> {
    operator fun invoke(id: P?): Flow<R>
}