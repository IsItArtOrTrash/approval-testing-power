package io.github.isitartortrash.approvaltesting.livecoding

class CouponBuilder {
    private var id: String? = null
    private var description: String? = null
    private var reducedRateInPercentage: Int? = null

    fun id(id: String?): CouponBuilder {
        this.id = id
        return this
    }

    fun description(description: String?): CouponBuilder {
        this.description = description
        return this
    }

    fun reducedRateInPercentage(reducedRateInPercentage: Int): CouponBuilder {
        this.reducedRateInPercentage = reducedRateInPercentage
        return this
    }

    fun build(): ShopCoupon {
        return ShopCoupon(id!!, description!!, reducedRateInPercentage!!)
    }

    companion object {
        @JvmStatic
        fun aCoupon(): CouponBuilder {
            return CouponBuilder()
        }
    }
}
