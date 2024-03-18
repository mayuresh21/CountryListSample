package com.mayuresh.countries.data.mapper

/**
 * This interface is used if we wnat to map complete response to desired response
 */
interface Mapper<F,T> {
    /**
     * This method has one parameter which is source and returns
     * the desired result
     */
    fun mapFrom(from:F):T
}