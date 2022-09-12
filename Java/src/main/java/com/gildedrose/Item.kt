@file:Suppress("NAME_SHADOWING")

package com.gildedrose

open class Item(var name: String, var sellIn: Int = 0, var quality: Int = 0) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

class BaseItem(name: String,
               sellIn: Int = 0,
               quality: Int = 0,
               private val aging: () -> Int = { 1 },
               private val degradation: (Int, Int) -> Int = { sellIn: Int, _: Int -> if (sellIn < 0) 2 else 1 },
               private val saturation: (Int) -> Int = fun(quality: Int): Int = when {
                   quality < 0 -> 0
                   quality > 50 -> 50
                   else -> quality
               })
    : Item(name, sellIn, quality) {
    open fun update() {
        sellIn -= aging()
        quality = saturation(quality - degradation(sellIn, quality))
    }

}


fun Sulfuras(name: String, sellIn: Int = 0, quality: Int = 0) = BaseItem(
    name,
    sellIn,
    quality,
    aging = { 0 },
    degradation = { _, _ -> 0 },
    saturation = { it })


fun Brie(name: String, sellIn: Int = 0, quality: Int = 0) = BaseItem(
    name,
    sellIn,
    quality,
    degradation = { sellIn, _ -> if (sellIn < 0) -2 else -1 })


fun Pass(name: String, sellIn: Int = 0, quality: Int = 0) = BaseItem(
    name,
    sellIn,
    quality,
    degradation = { sellIn: Int, quality: Int ->
        when {
            sellIn < 0 -> quality
            sellIn < 5 -> -3
            sellIn < 10 -> -2
            else -> -1
        }
    })
