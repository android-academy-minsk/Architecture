package com.mvvmsample.mappers

interface PresentationMapper<E, R> {

    fun transform(entity: E): R

    fun transform(entities: List<E>): List<R> = entities.map(::transform)
}