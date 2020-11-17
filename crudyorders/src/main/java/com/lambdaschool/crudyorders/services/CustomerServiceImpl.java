package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;
import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.repositories.AgentRepository;
import com.lambdaschool.crudyorders.repositories.CustomerRepository;
import com.lambdaschool.crudyorders.views.CustCountOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AgentRepository agentRepository;


//    @Transactional
//    @Override
//    public Customer save(Customer customer) {
//
//        Customer newCustomer = new Customer();
//
//        if (customer.getCustcode() != 0) {
//            customerRepository.findById(customer.getCustcode())
//                    .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));
//        }
//
//        // Populate object fields
//        newCustomer.setCustname(customer.getCustname());
//        newCustomer.setCustcity(customer.getCustcity());
//        newCustomer.setWorkingarea(customer.getWorkingarea());
//        newCustomer.setCustcountry(customer.getCustcountry());
//        newCustomer.setGrade(customer.getGrade());
//        newCustomer.setOpeningamt(customer.getOpeningamt());
//        newCustomer.setReceiveamt(customer.getReceiveamt());
//        newCustomer.setPaymentamt(customer.getPaymentamt());
//        newCustomer.setOutstandingamt(customer.getOutstandingamt());
//        newCustomer.setPhone(customer.getPhone());
//        newCustomer.setAgent(customer.getAgent());
//
//        // Populate Lists
//        newCustomer.getOrders().clear();
//
//        for (Order o : customer.getOrders()) {
//            Order newOrder = new Order(o.getOrdamount(), o.getAdvanceamount(),
//                    o.getOrderdescription(), newCustomer);
//            newCustomer.getOrders().add(newOrder);
//        }
//
//        return customerRepository.save(newCustomer);
//    }


    @Transactional
    @Override
    public Customer save(Customer customer) {

        Customer newCustomer = new Customer();

        // Replace condition
        if (customer.getCustcode() != 0) {
            customerRepository.findById(customer.getCustcode())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Customer " + customer.getCustcode() + " not found"));
            newCustomer.setCustcode(customer.getCustcode());
        }

        // Data validation
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders()) {
            Order newOrder = new Order(
                    o.getOrdamount(),
                    o.getAdvanceamount(),
                    o.getOrderdescription(),
                    newCustomer);
            newCustomer.getOrders().add(newOrder);
        }

        return customerRepository.save(newCustomer);
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

    @Transactional
    @Override
    public Customer update(Customer customer, long custcode) {

        Customer currentCustomer = customerRepository.findByCustcode(custcode);

        if (currentCustomer == null) {
            throw new EntityNotFoundException(("Customer " + custcode + " not found"));
        }

        // Data validation
        if (customer.getCustname() != null)
            currentCustomer.setCustname(customer.getCustname());

        if (customer.getCustcity() != null)
            currentCustomer.setCustcity(customer.getCustcity());

        if (customer.getWorkingarea() != null)
            currentCustomer.setWorkingarea(customer.getWorkingarea());

        if (customer.getCustcountry() != null)
            currentCustomer.setCustcountry(customer.getCustcountry());

        if (customer.getGrade() != null)
            currentCustomer.setGrade(customer.getGrade());

        if (customer.hasvalueforopeningamt)
            currentCustomer.setOpeningamt(customer.getOpeningamt());

        if (customer.hasvalueforreceiveamt)
            currentCustomer.setReceiveamt(customer.getReceiveamt());

        if (customer.hasvalueforpaymentamt)
            currentCustomer.setPaymentamt(customer.getPaymentamt());

        if (customer.hasvalueforoutstandingamt)
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());

        if (customer.getPhone() != null)
            currentCustomer.setPhone(customer.getPhone());

        if (customer.getAgent() != null) {
            // Confirm agent exists
            long agentId = customer.getAgent().getAgentcode();
            Agent a = agentRepository.findById(agentId)
                    .orElseThrow(() -> new EntityNotFoundException("" +
                            "Agent " + agentId + " not found"));
            currentCustomer.setAgent(a);
        }



        currentCustomer.getOrders().clear();

        for (Order o : customer.getOrders()) {
            Order newOrder = new Order(
                    o.getOrdamount(),
                    o.getAdvanceamount(),
                    o.getOrderdescription(),
                    currentCustomer);
            currentCustomer.getOrders().add(newOrder);
        }

        return customerRepository.save(currentCustomer);
    }

    @Transactional
    @Override
    public void delete(long custcode) {
        Customer c = customerRepository.findByCustcode(custcode);

        if (c == null) {
            throw new EntityNotFoundException(("Customer " + custcode + " not found"));
        }

        customerRepository.delete(c);
    }
}

