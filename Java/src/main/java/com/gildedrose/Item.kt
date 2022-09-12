@file:Suppress("NAME_SHADOWING")

package com.gildedrose

open class Item(var name: String, var sellIn: Int = 0, var quality: Int = 0) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

object Aging {
    val standard: () -> Int = { 1 }
    val none: () -> Int = { 1 }

}

object Degradation {
    val standard: (Int, Int) -> Int = { sellIn: Int, _: Int -> if (sellIn < 0) 2 else 1 }
    val none: (Int, Int) -> Int = { _, _ -> 0 }

}

object Saturation {
    val standard: (Int) -> Int = fun(quality: Int): Int = when {
        quality < 0 -> 0
        quality > 50 -> 50
        else -> quality
    }
    val none: (Int) -> Int = { it }

}


class BaseItem(name: String,
               sellIn: Int = 0,
               quality: Int = 0,
               private val aging: () -> Int = Aging.standard,
               private val degradation: (Int, Int) -> Int = Degradation.standard,
               private val saturation: (Int) -> Int = Saturation.standard)
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
    aging = Aging.none,
    degradation = Degradation.none,
    saturation = Saturation.none)


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

fun Conjured(name: String, sellIn: Int = 0, quality: Int = 0) = BaseItem(
    name,
    sellIn,
    quality,
    degradation = { sellIn: Int, quality: Int ->
        when {
            sellIn < 0 -> 4
            else -> 2
        }
    })
