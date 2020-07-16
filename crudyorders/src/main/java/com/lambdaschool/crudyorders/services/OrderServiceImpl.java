package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.models.Payment;
import com.lambdaschool.crudyorders.repositories.OrderRepository;
import com.lambdaschool.crudyorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    @Override
    public Order save(Order order) {

        Order newOrder = new Order();

        long custid = order.getCustomer().getCustcode();
        Customer c = customerService.findById(custid);

        // Replace condition
        if (order.getOrdnum() != 0) {
            orderRepository.findById(order.getOrdnum())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Order " + order.getOrdnum() + " not found"));
            newOrder.setOrdnum(order.getOrdnum());
        }

        // Data validation
        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(c);

        newOrder.getPayments().clear();

        for (Payment p : order.getPayments()) {
            Payment newPay = paymentRepository.findById(p.getPaymentid())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Payment " + p.getPaymentid() + " not found"));
            newOrder.getPayments().add(newPay);
        }

        return orderRepository.save(newOrder);
    }

    @Override
    public Order findById(long id) {
        Order o = orderRepository.findByOrdnum(id);

        if (o == null) {
            throw new EntityNotFoundException(("Order number " + id + " not found"));
        }

        return o;
    }

    @Transactional
    @Override
    public void delete(long id) {
        Order o = orderRepository.findByOrdnum(id);

        if (o == null) {
            throw new EntityNotFoundException(("Order " + id + " not found"));
        }

        orderRepository.delete(o);
    }
}
