package com.polendina.kallery.utils

/**
 * Extension function to create a camel case of the given sentence paragraph.
 */
fun String.titleCase() = this.split(" ").map { it.first().uppercase() + it.substring(1, it.length) }.joinToString(" ")