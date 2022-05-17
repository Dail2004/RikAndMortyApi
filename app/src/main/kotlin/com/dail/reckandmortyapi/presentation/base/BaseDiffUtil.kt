package com.dail.reckandmortyapi.presentation.base

interface BaseDiffUtil {
    val id: Int
    override fun equals(other: Any?): Boolean
}