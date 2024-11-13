package io.github.isitartortrash.approvaltesting.livecoding

class CustomerBuilder {
    private var id: String? = null
    private var firstName: String? = null
    private var lastName: String? = null

    fun id(id: String?): CustomerBuilder {
        this.id = id
        return this
    }

    fun firstName(firstName: String?): CustomerBuilder {
        this.firstName = firstName
        return this
    }

    fun lastName(lastName: String?): CustomerBuilder {
        this.lastName = lastName
        return this
    }

    fun build(): ShopCustomer {
        return ShopCustomer(id!!, firstName!!, lastName!!)
    }

    companion object {
        @JvmStatic
        fun aCustomer(): CustomerBuilder {
            return CustomerBuilder()
        }
    }
}
