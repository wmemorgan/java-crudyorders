package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.repositories.CustomerRepository;
import com.lambdaschool.crudyorders.views.CustCountOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> list = new ArrayList<>();
        customerRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Override
    public Customer findById(long id) {
        Customer c = customerRepository.findByCustcode(id);

        if (c == null) {
            throw new EntityNotFoundException(("Customer " + id + " not found"));
        }

        return c;
    }

    @Override
    public List<Customer> findByNameLike(String likename) {
        List<Customer> list = customerRepository.findByCustnameContainingIgnoringCase(likename);

        if (list == null) {
            throw new EntityNotFoundException(("Customer " + likename + " not found"));
        }

        return list;
    }

    @Override
    public List<CustCountOrders> getCustCountOrders() {
        return customerRepository.getCustCountOrders();
    }
}

