package com.gildedrose

open class Item(var name: String, var sellIn: Int = 0, var quality: Int = 0) {
    override fun toString(): String = "$name, $sellIn, $quality"
}

open class BaseItem(name: String, sellIn: Int = 0, quality: Int = 0) : Item(name, sellIn, quality) {
    open fun update() {
        val name = name
        age()
        degrade()
    }

    protected open fun age() {
        sellIn -= 1
    }

    protected open fun degrade() {
        if (quality > 0)
            quality -= 1
        if (sellIn < 0) {
            if (quality > 0) {
                quality -= 1
            }
        }
    }

}

class Brie(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
        if (quality < 50) {
            quality += 1
        }
        if (sellIn < 0) {
            if (quality < 50) {
                quality += 1
            }
        }
    }

    override fun age() {
        sellIn -= 1
    }


}

class Sulfuras(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
    }

    override fun age() {
    }

}


class Pass(name: String, sellIn: Int = 0, quality: Int = 0) : BaseItem(name, sellIn, quality) {
    override fun degrade() {
        if (quality < 50) {
            quality += 1
            if (sellIn < 10) {
                if (quality < 50) {
                    quality += 1
                }
            }
            if (sellIn < 5) {
                if (quality < 50) {
                    quality += 1
                }
            }
        }
        if (sellIn < 0) {
            quality = 0
        }
    }

    override fun age() {
        sellIn -= 1
    }


}
