package com.example.hacktues9_bisi.data

import com.example.hacktues9_bisi.data.server.Customer
import com.example.hacktues9_bisi.data.server.CustomerEntity

open class EntityMapper {
    open fun mapToCustomerEntity(customer: Customer): CustomerEntity{
        return CustomerEntity(
            id = customer.id,
            riskPercentage = customer.riskPercentage
        )
    }

    open fun mapFromCustomerEntity(customerEntity: CustomerEntity): Customer{
        return Customer(
            id = customerEntity.id,
            riskPercentage = customerEntity.riskPercentage
        )
    }
}