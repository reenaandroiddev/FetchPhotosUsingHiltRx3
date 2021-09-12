package com.learn.fetchphotos.data.mapper

interface Mapper<in From, out To> {
    fun doMapping(from: From): To
}