package com.example.realstate.utils

import kotlinx.coroutines.flow.Flow

interface FLowUseCase<in P, R> {
    operator fun invoke(data: P): Flow<R>
}