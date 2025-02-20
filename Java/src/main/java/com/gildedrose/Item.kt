package com.gildedrose

data class Item(val name: String,
                val sellIn: Int,
                val quality: Int,
                private val aging: () -> Int = Aging.standard,
                private val degradation: (Int, Int) -> Int = Degradation.standard,
                private val saturation: (Int) -> Int = Saturation.standard) {
    override fun toString(): String = "$name, $sellIn, $quality"

    fun updated(): Item {
        val sellIn = sellIn - aging()
        return this.copy(
            sellIn = sellIn,
            quality = saturation(quality - degradation(sellIn, quality)),
        )
    }

}
