package com.example.hacktues9_bisi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hacktues9_bisi.data.server.Customer
import com.example.hacktues9_bisi.repository.CustomersRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val customersRepository: CustomersRepositoryImpl
) : ViewModel() {
    private val _customers = MutableStateFlow<List<Customer>>(emptyList())
    val customers: StateFlow<List<Customer>>
        get() = _customers

    init {
        getCustomers()
    }

    private fun getCustomers(){
        viewModelScope.launch {
            _customers.value = customersRepository.getCustomers()
        }
    }
}