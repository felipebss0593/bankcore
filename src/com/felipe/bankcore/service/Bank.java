package com.felipe.bankcore.service;

import com.felipe.bankcore.model.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia clientes do sistema bancário.
 */
public class Bank {
    private List<Customer> customerList = new ArrayList<>();

    /**
     * adiciona o cliente criado na lista de customerList.
     * @param name é o nome do cliente a ser cadastrado.
     * @return o cliente criado.
     */
    public Customer addCustomer(String name){
        Customer customer = new Customer(name);
        customerList.add(customer);
        return customer;
    }

    /**
     * Percorre a lista para achar o cliente com o id informado.
     * @param id id do cliente a ser buscado.
     * @return o cliente encontrado, ou null se nenhum cliente tiver esse id.
     */
    public Customer findCustomerById(int id){
        for(Customer customer: customerList){
            if(customer.getId() == id){
                return customer;
            }
        }
        return null;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
