package com.example.hacktues9_bisi.repository

import com.example.hacktues9_bisi.data.EntityMapper
import com.example.hacktues9_bisi.data.server.Customer
import com.example.hacktues9_bisi.data.server.CustomerService

class CustomersRepositoryImpl(
    private val customerService: CustomerService,
    private val mapper: EntityMapper
) : CustomersRepository {

    override suspend fun getCustomers(): List<Customer> {
        return customerService.getCustomers().customers.map {
            mapper.mapFromCustomerEntity(it)
        }
    }
}