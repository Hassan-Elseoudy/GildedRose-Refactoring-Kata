package com.gildedrose

open class Item(var name: String, var sellIn: Int = 0, var quality: Int = 0) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

open class BaseItem(name: String, sellIn: Int = 0, quality: Int = 0) : Item(name, sellIn, quality) {
    open fun update() {
        val name = name
        update1()
        if (name != "Sulfuras, Hand of Ragnaros") {
            sellIn -= 1
        }
        if (sellIn < 0) {
            if (name != "Aged Brie") {
                if (name != "Backstage passes to a TAFKAL80ETC concert") {
                    if (quality > 0) {
                        if (name != "Sulfuras, Hand of Ragnaros") {
                            quality -= 1
                        }
                    }
                } else {
                    quality -= quality
                }
            } else {
                if (quality < 50) {
                    quality += 1
                }
            }
        }
    }

    protected open fun update1() {
        if (quality > 0)
            quality -= 1
    }

}

class Brie(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun update1() {
        if (quality < 50) {
            quality += 1
        }
    }

}

class Sulfuras(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun update1() {
    }

}


class Pass(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun update1() {
        if (quality < 50) {
            quality += 1
            if (sellIn < 11) {
                if (quality < 50) {
                    quality += 1
                }
            }
            if (sellIn < 6) {
                if (quality < 50) {
                    quality += 1
                }
            }
        }
    }

}
