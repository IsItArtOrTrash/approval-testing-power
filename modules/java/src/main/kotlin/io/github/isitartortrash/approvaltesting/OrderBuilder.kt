package io.github.isitartortrash.approvaltesting

import java.time.LocalDate

class OrderBuilder {
    private var id: String? = null
    private var version: Int? = null
    private var shopItems: List<ShopItem>? = null
    private var shopCoupons: List<ShopCoupon> = emptyList()
    private var deliveryDate: LocalDate? = null
    private var shopCustomer: ShopCustomer? = null
    private var shippingShopAddress: ShopAddress? = null
    private var billingShopAddress: ShopAddress? = null

    fun id(id: String): OrderBuilder {
        this.id = id
        return this
    }

    fun version(version: Int): OrderBuilder {
        this.version = version
        return this
    }

    fun items(shopItems: List<ShopItem>): OrderBuilder {
        this.shopItems = shopItems
        return this
    }

    fun coupons(shopCoupons: List<ShopCoupon>): OrderBuilder {
        this.shopCoupons = shopCoupons
        return this
    }

    fun deliveryDate(deliveryDate: LocalDate): OrderBuilder {
        this.deliveryDate = deliveryDate
        return this
    }

    fun customer(shopCustomer: ShopCustomer): OrderBuilder {
        this.shopCustomer = shopCustomer
        return this
    }

    fun shippingAddress(shippingShopAddress: ShopAddress): OrderBuilder {
        this.shippingShopAddress = shippingShopAddress
        return this
    }

    fun billingAddress(billingShopAddress: ShopAddress): OrderBuilder {
        this.billingShopAddress = billingShopAddress
        return this
    }

    fun build(): ShopOrder {
        return ShopOrder(
            id = id!!,
            version = version!!,
            items = shopItems!!,
            coupons = shopCoupons,
            deliveryDate = deliveryDate!!,
            customer = shopCustomer!!,
            shippingAddress = shippingShopAddress!!,
            billingAddress = billingShopAddress!!
        )
    }

    companion object {
        @JvmStatic
        fun anOrder(): OrderBuilder {
            return OrderBuilder()
        }
    }
}
