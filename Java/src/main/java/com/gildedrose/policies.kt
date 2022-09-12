package com.gildedrose

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
