package com.example.currencytracker

interface Mapper<T,R> {

    fun map(entity: T) : R
}