package com.gildedrose

open class Item(var name: String, var sellIn: Int = 0, var quality: Int = 0) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

open class BaseItem(name: String, sellIn: Int = 0, quality: Int = 0, private val aging: () -> Int = fun() = 1) : Item(name, sellIn, quality) {
    open fun update() {
        val name = name
        sellIn -= aging()
        quality = saturation(quality - degradation(sellIn))
    }

    protected open fun degradation(sellIn: Int): Int = if (sellIn < 0) 2 else 1

    protected open fun saturation(quality: Int): Int = when {
        quality < 0 -> 0
        quality > 50 -> 50
        else -> quality
    }

}

class Brie(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun degradation(sellIn: Int): Int = if (sellIn < 0) -2 else -1

}

class Sulfuras(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality, aging = { 0 }) {

    override fun degradation(sellIn: Int): Int = 0

    override fun saturation(quality: Int): Int = quality


}


class Pass(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {

    override fun degradation(sellIn: Int): Int = when {
        sellIn < 0 -> quality
        sellIn < 5 -> -3
        sellIn < 10 -> -2
        else -> -1
    }

}
