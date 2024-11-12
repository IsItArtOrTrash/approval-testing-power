package io.github.isitartortrash.approvaltesting

class PriceBuilder {
    private var value: Int? = null
    private var monetaryUnit: String? = null
    private var currency: String? = null

    fun value(value: Int): PriceBuilder {
        this.value = value
        return this
    }

    fun monetaryUnit(monetaryUnit: String?): PriceBuilder {
        this.monetaryUnit = monetaryUnit
        return this
    }

    fun currency(currency: String?): PriceBuilder {
        this.currency = currency
        return this
    }

    fun build(): ShopPrice {
        return ShopPrice(value!!, monetaryUnit!!, currency!!)
    }

    companion object {
        @JvmStatic
        fun aPrice(): PriceBuilder {
            return PriceBuilder()
        }
    }
}
