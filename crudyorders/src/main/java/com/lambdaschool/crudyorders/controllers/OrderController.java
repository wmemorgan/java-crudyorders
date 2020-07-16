package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.services.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    // http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> findOrderById(@PathVariable long id) {
        Order o = orderService.findById(id);

        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    // POST http://localhost:2019/orders/order
    @PostMapping(value = "/order", consumes = {"application/json"})
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody Order newOrder) {
        newOrder.setOrdnum(0);
        newOrder = orderService.save(newOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ordnum}")
                .buildAndExpand(newOrder.getOrdnum())
                .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/orders/order/{ordnum}
    @PutMapping(value = "/order/{ordnum}", consumes = {"application/json"})
    public ResponseEntity<?> replaceOrder(@Valid @RequestBody Order replaceOrder,
                                          @PathVariable long ordnum) {
        replaceOrder.setOrdnum(ordnum);
        orderService.save(replaceOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/orders/order/{ordnum}
    @DeleteMapping(value = "/order/{ordnum}", produces = {"application/json"})
    public ResponseEntity<?> deleteOrder(@PathVariable long ordnum) {
        orderService.delete(ordnum);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
