package io.github.isitartortrash.approvaltesting

class AddressResultBuilder {
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
    private var latitude: String? = null
    private var longitude: String? = null
    private var status: CustomerStatus? = null

    fun id(id: String?): AddressResultBuilder {
        this.id = id
        return this
    }

    fun firstName(firstName: String?): AddressResultBuilder {
        this.firstName = firstName
        return this
    }

    fun lastName(lastName: String?): AddressResultBuilder {
        this.lastName = lastName
        return this
    }

    fun streetName(streetName: String?): AddressResultBuilder {
        this.streetName = streetName
        return this
    }

    fun houseNumber(houseNumber: String?): AddressResultBuilder {
        this.houseNumber = houseNumber
        return this
    }

    fun postalCode(postalCode: String?): AddressResultBuilder {
        this.postalCode = postalCode
        return this
    }

    fun city(city: String?): AddressResultBuilder {
        this.city = city
        return this
    }

    fun country(country: String?): AddressResultBuilder {
        this.country = country
        return this
    }

    fun phone(phone: String?): AddressResultBuilder {
        this.phone = phone
        return this
    }

    fun email(email: String?): AddressResultBuilder {
        this.email = email
        return this
    }

    fun latitude(latitude: String?): AddressResultBuilder {
        this.latitude = latitude
        return this
    }

    fun longitude(longitude: String?): AddressResultBuilder {
        this.longitude = longitude
        return this
    }

    fun status(status: CustomerStatus?): AddressResultBuilder {
        this.status = status
        return this
    }

    fun build(): AddressResult {
        return AddressResult(
            id = id!!,
            firstName = firstName!!,
            lastName = lastName!!,
            streetName = streetName!!,
            houseNumber = houseNumber!!,
            city = city!!,
            country = country!!,
            phone = phone!!,
            email = email!!,
            postalCode = postalCode!!,
            latitude = latitude!!,
            longitude = longitude!!,
            status = status!!
        )
    }

    companion object {
        @JvmStatic
        fun anAddressResult(): AddressResultBuilder {
            return AddressResultBuilder()
        }
    }
}
