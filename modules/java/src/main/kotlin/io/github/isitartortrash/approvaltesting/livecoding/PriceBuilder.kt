package io.github.isitartortrash.approvaltesting.livecoding

class PriceBuilder {
    private var value: Int? = null
    private var currency: Currency? = null

    fun value(value: Int): PriceBuilder {
        this.value = value
        return this
    }

    fun currency(currency: Currency): PriceBuilder {
        this.currency = currency
        return this
    }

    fun build(): ShopPrice {
        return ShopPrice(value!!, currency!!)
    }

    companion object {
        @JvmStatic
        fun aPrice(): PriceBuilder {
            return PriceBuilder()
        }
    }
}
