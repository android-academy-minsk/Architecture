package com.cleanarchitecturesample.mappers

interface PresentationMapper<E, R> {

    fun transform(entity: E): R

    fun transform(entities: List<E>): List<R> {
        val result = ArrayList<R>()
        entities.forEach { result.add(transform(it)) }
        return result
    }
}