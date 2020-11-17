package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.services.CustomerService;
import com.lambdaschool.crudyorders.views.CustCountOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers () {
        List<Customer> myList = customerService.findAllCustomers();

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerById (@PathVariable long id) {
        Customer c = customerService.findById(id);

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike/{likename}
    @GetMapping(value = "/namelike/{likename}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerByNameLike (@PathVariable String likename) {
        List<Customer> myList = customerService.findByNameLike(likename);

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // http://localhost:2019/customers/orders/count
    @GetMapping(value = "/orders/count", produces = {"application/json"})
    public ResponseEntity<?> listCustomersByOrderCount() {
        List<CustCountOrders> myList = customerService.getCustCountOrders();

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customer newCustomer) {
        newCustomer.setCustcode(0);
        newCustomer = customerService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{custcode}")
                .buildAndExpand(newCustomer.getCustcode())
                .toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/customers/customer/{custcode}
    @PutMapping(value = "/customer/{custcode}", consumes = {"application/json"})
    public ResponseEntity<?> replaceCustomer(@Valid @RequestBody Customer replaceCustomer,
                                             @PathVariable long custcode) {
        replaceCustomer.setCustcode(custcode);
        customerService.save(replaceCustomer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // PATCH http://localhost:2019/customers/customer/{custcode}
    @PatchMapping(value = "/customer/{custcode}", consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody Customer updateCustomer,
                                            @PathVariable long custcode) {
        updateCustomer.setCustcode(custcode);
        customerService.update(updateCustomer, custcode);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/customers/customer/{custcode}
    @DeleteMapping(value = "/customer/{custcode}", produces = {"application/json"})
    public ResponseEntity<?> deleteCustomer(@PathVariable long custcode) {
        customerService.delete(custcode);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
