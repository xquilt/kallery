package com.polendina.kallery.utils

import org.junit.After
import org.junit.Before
import org.junit.Test


class StringUtilsKtTest {

    lateinit var words: MutableList<String>

    @Before
    fun setUp() {
        words = mutableListOf("adams", "sam", "luke in the duke")
    }

    @Test
    fun titleCase() {
        println(words.last().titleCase())
    }

    @After
    fun tearDown() {
    }

}