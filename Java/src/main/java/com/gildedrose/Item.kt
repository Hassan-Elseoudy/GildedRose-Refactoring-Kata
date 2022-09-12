package com.gildedrose

open class Item(var name: String, var sellIn: Int = 0, var quality: Int = 0,
                private val aging: () -> Int = Aging.standard,
                private val degradation: (Int, Int) -> Int = Degradation.standard,
                private val saturation: (Int) -> Int = Saturation.standard) {
    override fun toString(): String = "$name, $sellIn, $quality"

    open fun update() {
        sellIn -= aging()
        quality = saturation(quality - degradation(sellIn, quality))
    }

}
