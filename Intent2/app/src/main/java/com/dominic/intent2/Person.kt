package com.dominic.intent2

import java.io.Serializable

data class Person(
    val name: String,
    val age: Int,
    val country: String
): Serializable
