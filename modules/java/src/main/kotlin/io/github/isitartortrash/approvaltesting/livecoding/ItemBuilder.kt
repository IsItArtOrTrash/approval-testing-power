package io.github.isitartortrash.approvaltesting.livecoding

class ItemBuilder {
    private var id: String? = null
    private var name: String? = null
    private var amount: Int? = null
    private var shopPrice: ShopPrice? = null

    fun id(id: String?): ItemBuilder {
        this.id = id
        return this
    }

    fun name(name: String?): ItemBuilder {
        this.name = name
        return this
    }

    fun amount(amount: Int): ItemBuilder {
        this.amount = amount
        return this
    }

    fun price(shopPrice: ShopPrice?): ItemBuilder {
        this.shopPrice = shopPrice
        return this
    }

    fun build(): ShopItem {
        return ShopItem(id!!, name!!, amount!!, shopPrice!!)
    }

    companion object {
        @JvmStatic
        fun anItem(): ItemBuilder {
            return ItemBuilder()
        }
    }
}
