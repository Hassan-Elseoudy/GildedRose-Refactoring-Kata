package com.gildedrose

typealias NewGildedRose = List<Item>
fun NewGildedRose.updated() = this.map { it.updated() }
val NewGildedRose.items: List<Item> get() = this
