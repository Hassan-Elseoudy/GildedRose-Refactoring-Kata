package com.gildedrose

class GildedRose(val items: List<Item>) {
    fun updated(): GildedRose = GildedRose(items = items.map { it.updated() })

}
