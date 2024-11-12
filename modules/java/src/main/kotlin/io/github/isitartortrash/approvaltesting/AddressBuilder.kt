package io.github.isitartortrash.approvaltesting

class AddressBuilder {
    private var id: String? = null
    private var firstName: String? = null
    private var lastName: String? = null
    private var streetName: String? = null
    private var houseNumber: String? = null
    private var postalCode: String? = null
    private var city: String? = null
    private var country: String? = null
    private var phone: String? = null
    private var email: String? = null

    fun id(id: String?): AddressBuilder {
        this.id = id
        return this
    }

    fun firstName(firstName: String?): AddressBuilder {
        this.firstName = firstName
        return this
    }

    fun lastName(lastName: String?): AddressBuilder {
        this.lastName = lastName
        return this
    }

    fun streetName(streetName: String?): AddressBuilder {
        this.streetName = streetName
        return this
    }

    fun houseNumber(houseNumber: String?): AddressBuilder {
        this.houseNumber = houseNumber
        return this
    }

    fun postalCode(postalCode: String?): AddressBuilder {
        this.postalCode = postalCode
        return this
    }

    fun city(city: String?): AddressBuilder {
        this.city = city
        return this
    }

    fun country(country: String?): AddressBuilder {
        this.country = country
        return this
    }

    fun phone(phone: String?): AddressBuilder {
        this.phone = phone
        return this
    }

    fun email(email: String?): AddressBuilder {
        this.email = email
        return this
    }

    fun build(): ShopAddress {
        return ShopAddress(
            id = id!!,
            firstName = firstName!!,
            lastName = lastName!!,
            streetName = streetName!!,
            houseNumber = houseNumber!!,
            city = city!!,
            country = country!!,
            phone = phone!!,
            email = email!!,
            postalCode = postalCode!!
        )
    }

    companion object {
        @JvmStatic
        fun anAddress(): AddressBuilder {
            return AddressBuilder()
        }
    }
}
