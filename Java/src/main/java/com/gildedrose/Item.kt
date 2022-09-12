package com.gildedrose

open class Item(var name: String, var sellIn: Int = 0, var quality: Int = 0) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

open class BaseItem(name: String, sellIn: Int = 0, quality: Int = 0) : Item(name, sellIn, quality) {
    open fun update() {
        val name = name
        update1()
        age()
        update2()
    }

    protected open fun update2() {
        if (sellIn < 0) {
            if (quality > 0) {
                quality -= 1
            }
        }
    }

    protected open fun age() {
        if (name != "Sulfuras, Hand of Ragnaros") {
            sellIn -= 1
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

    override fun age() {
        sellIn -= 1
    }

    override fun update2() {
        if (sellIn < 0) {
            if (quality < 50) {
                quality += 1
            }
        }
    }


}

class Sulfuras(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun update1() {
    }

    override fun age() {
    }

    override fun update2() {}


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

    override fun age() {
        sellIn -= 1
    }

    override fun update2() {
        if (sellIn < 0) {
            quality -= quality
        }
    }


}
