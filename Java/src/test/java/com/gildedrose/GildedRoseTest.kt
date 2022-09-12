package com.gildedrose

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class GildedRoseTest {
    @Test
    fun foo() {
        val items = listOf<Item>(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updated()
        Assertions.assertEquals("foo", app.items[0].name)
    }
}
