package com.gildedrose

open class Item(var name: String, var sellIn: Int = 0, var quality: Int = 0) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

open class BaseItem(name: String, sellIn: Int = 0, quality: Int = 0) : Item(name, sellIn, quality) {
    open fun update() {
        val name = name
        age()
        degrade()
        saturate()
    }

    protected open fun saturate() {
        if (quality < 0) quality = 0
        if (quality > 50) quality = 50
    }

    protected open fun age() {
        sellIn -= 1
    }

    protected open fun degrade() {
        quality -= if (sellIn < 0) 2 else 1
    }

}

class Brie(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
        quality += if (sellIn < 0) 2 else 1
    }

    override fun age() {
        sellIn -= 1
    }

    override fun saturate() {
        when {
            quality < 0 -> quality = 0
            quality > 50 -> quality = 50
        }
    }


}

class Sulfuras(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun degrade() {}

    override fun age() {}

    override fun saturate() {}

}


class Pass(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
        quality = when {
            sellIn < 0 -> 0
            sellIn < 5 -> quality + 3
            sellIn < 10 -> quality + 2
            else -> quality + 1

        }

    }

    override fun age() {
        sellIn -= 1
    }

    override fun saturate() {
        if (quality < 0) quality = 0
        if (quality > 50) quality = 50
    }


}
