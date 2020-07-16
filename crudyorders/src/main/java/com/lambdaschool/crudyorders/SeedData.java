package com.lambdaschool.crudyorders;

//import com.github.javafaker.Faker;

import com.lambdaschool.crudyorders.models.Agent;
import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.models.Payment;
import com.lambdaschool.crudyorders.services.AgentService;
import com.lambdaschool.crudyorders.services.CustomerService;
import com.lambdaschool.crudyorders.services.OrderService;
import com.lambdaschool.crudyorders.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


 @Transactional
 @Component
public class SeedData implements CommandLineRunner
{
    /**
     * Connects the customer table to this SeedData method
     */
    @Autowired
    private CustomerService customerService;

    /**
     * Connects the agents table to this SeedData method
     */
    @Autowired
    private AgentService agentService;

    /**
     * Connects the orders table to this SeedData method
     */
    @Autowired
    private OrderService orderService;

    /**
     * Connects the payment table to this SeedData method
     */
    @Autowired
    private PaymentService paymentService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        Payment pay1 = new Payment("Cash");
        Payment pay2 = new Payment("Gift Card");
        Payment pay3 = new Payment("Credit Card");
        Payment pay4 = new Payment("Mobile Pay");

        pay1 = paymentService.save(pay1);
        pay2 = paymentService.save(pay2);
        pay3 = paymentService.save(pay3);
        pay4 = paymentService.save(pay4);

        Agent a01 = new Agent("Ramasundar",
            "Bangalore",
            0.15,
            "077-25814763",
            "");
        Agent a02 = new Agent("Alex",
            "London",
            0.13,
            "075-12458969",
            "");
        Agent a03 = new Agent("Alford",
            "New York",
            0.12,
            "044-25874365",
            "");
        Agent a04 = new Agent("Ravi",
            "Bangalore",
            0.15,
            "077-45625874",
            "");
        Agent a05 = new Agent("Santakumar",
            "Chennai",
            0.14,
            "007-22388644",
            "");
        Agent a06 = new Agent("Lucida",
            "San Jose",
            0.12,
            "044-52981425",
            "");
        Agent a07 = new Agent("Anderson",
            "Brisban",
            0.13,
            "045-21447739",
            "");
        Agent a08 = new Agent("Subbarao",
            "Bangalore",
            0.14,
            "077-12346674",
            "");
        Agent a09 = new Agent("Mukesh",
            "Mumbai",
            0.11,
            "029-12358964",
            "");
        Agent a10 = new Agent("McDen",
            "London",
            0.15,
            "078-22255588",
            "");
        Agent a11 = new Agent("Ivan",
            "Torento",
            0.15,
            "008-22544166",
            "");
        Agent a12 = new Agent("Benjamin",
            "Hampshair",
            0.11,
            "008-22536178",
            "");

        Customer c01 = new Customer("Holmes",
            "London",
            "London",
            "UK",
            "2",
            6000.00,
            5000.00,
            7000.00,
            4000.00,
            "BBBBBBB",
            a03);
        Customer c02 = new Customer("Micheal",
            "New York",
            "New York",
            "USA",
            "2",
            3000.00,
            5000.00,
            2000.00,
            6000.00,
            "CCCCCCC",
            a08);
        Customer c03 = new Customer("Albert",
            "New York",
            "New York",
            "USA",
            "3",
            5000.00,
            7000.00,
            6000.00,
            6000.00,
            "BBBBSBB",
            a08);
        Customer c04 = new Customer("Ravindran",
            "Bangalore",
            "Bangalore",
            "India",
            "2",
            5000.00,
            7000.00,
            4000.00,
            8000.00,
            "AVAVAVA",
            a11);
        Customer c05 = new Customer("Cook",
            "London",
            "London",
            "UK",
            "2",
            4000.00,
            9000.00,
            7000.00,
            6000.00,
            "FSDDSDF",
            a06);
        Customer c06 = new Customer("Stuart",
            "London",
            "London",
            "UK",
            "1",
            6000.00,
            8000.00,
            3000.00,
            11000.00,
            "GFSGERS",
            a03);
        Customer c07 = new Customer("Bolt",
            "New York",
            "New York",
            "USA",
            "3",
            5000.00,
            7000.00,
            9000.00,
            3000.00,
            "DDNRDRH",
            a08);
        Customer c08 = new Customer("Fleming",
            "Brisban",
            "Brisban",
            "Australia",
            "2",
            7000.00,
            7000.00,
            9000.00,
            5000.00,
            "NHBGVFC",
            a05);
        Customer c09 = new Customer("Jacks",
            "Brisban",
            "Brisban",
            "Australia",
            "1",
            7000.00,
            7000.00,
            7000.00,
            7000.00,
            "WERTGDF",
            a05);
        Customer c10 = new Customer("Yearannaidu",
            "Chennai",
            "Chennai",
            "India",
            "1",
            8000.00,
            7000.00,
            7000.00,
            8000.00,
            "ZZZZBFV",
            a10);
        Customer c11 = new Customer("Sasikant",
            "Mumbai",
            "Mumbai",
            "India",
            "1",
            7000.00,
            11000.00,
            7000.00,
            11000.00,
            "147-25896312",
            a02);
        Customer c12 = new Customer("Ramanathan",
            "Chennai",
            "Chennai",
            "India",
            "1",
            7000.00,
            11000.00,
            9000.00,
            9000.00,
            "GHRDWSD",
            a10);
        Customer c13 = new Customer("Avinash",
            "Mumbai",
            "Mumbai",
            "India",
            "2",
            7000.00,
            11000.00,
            9000.00,
            9000.00,
            "113-12345678",
            a02);
        Customer c14 = new Customer("Winston",
            "Brisban",
            "Brisban",
            "Australia",
            "1",
            5000.00,
            8000.00,
            7000.00,
            6000.00,
            "AAAAAAA",
            a05);
        Customer c15 = new Customer("Karl",
            "London",
            "London",
            "UK",
            "0",
            4000.00,
            6000.00,
            7000.00,
            3000.00,
            "AAAABAA",
            a06);
        Customer c16 = new Customer("Shilton",
            "Torento",
            "Torento",
            "Canada",
            "1",
            10000.00,
            7000.00,
            6000.00,
            11000.00,
            "DDDDDDD",
            a04);
        Customer c17 = new Customer("Charles",
            "Hampshair",
            "Hampshair",
            "UK",
            "3",
            6000.00,
            4000.00,
            5000.00,
            5000.00,
            "MMMMMMM",
            a09);
        Customer c18 = new Customer("Srinivas",
            "Bangalore",
            "Bangalore",
            "India",
            "2",
            8000.00,
            4000.00,
            3000.00,
            9000.00,
            "AAAAAAB",
            a07);
        Customer c19 = new Customer("Steven",
            "San Jose",
            "San Jose",
            "USA",
            "1",
            5000.00,
            7000.00,
            9000.00,
            3000.00,
            "KRFYGJK",
            a10);
        Customer c20 = new Customer("Karolina",
            "Torento",
            "Torento",
            "Canada",
            "1",
            7000.00,
            7000.00,
            9000.00,
            5000.00,
            "HJKORED",
            a04);
        Customer c21 = new Customer("Martin",
            "Torento",
            "Torento",
            "Canada",
            "2",
            8000.00,
            7000.00,
            7000.00,
            8000.00,
            "MJYURFD",
            a04);
        Customer c22 = new Customer("Ramesh",
            "Mumbai",
            "Mumbai",
            "India",
            "3",
            8000.00,
            7000.00,
            3000.00,
            12000.00,
            "Phone No",
            a02);
        Customer c23 = new Customer("Rangarappa",
            "Bangalore",
            "Bangalore",
            "India",
            "2",
            8000.00,
            11000.00,
            7000.00,
            12000.00,
            "AAAATGF",
            a01);
        Customer c24 = new Customer("Venkatpati",
            "Bangalore",
            "Bangalore",
            "India",
            "2",
            8000.00,
            11000.00,
            7000.00,
            12000.00,
            "JRTVFDD",
            a07);
        Customer c25 = new Customer("Sundariya",
            "Chennai",
            "Chennai",
            "India",
            "3",
            7000.00,
            11000.00,
            7000.00,
            11000.00,
            "PPHGRTS",
            a10);

        Order o01 = new Order(1000.00,
            600.00, "SOD",
            c13);
        o01.getPayments().add(pay1);

        Order o02 = new Order(3000.00,
            500.00, "SOD",
            c19);
        o02.getPayments().add(pay2);

        Order o03 = new Order(4500.00,
            900.00, "SOD",
            c07);
        o03.getPayments().add(pay3);
        o03.getPayments().add(pay2);

        Order o04 = new Order(2000.00,
            0.00, "SOD", c16);
        o04.getPayments().add(pay4);

        Order o05 = new Order(4000.00,
            600.00,"SOD", c22);
        o05.getPayments().add(pay2);

        Order o06 = new Order(2000.00,
            0.00, "SOD",
            c12);
        o06.getPayments().add(pay3);

        Order o07 = new Order(3500.00,
            2000.00, "SOD", c02);
        o07.getPayments().add(pay4);

        Order o08 = new Order(2500.00,
            400.00, "SOD", c03);
        o08.getPayments().add(pay1);

        Order o09 = new Order(500.00,
            0.00, "SOD", c23);
        o09.getPayments().add(pay3);

        Order o10 = new Order(4000.00,
            700.00, "SOD", c07);
        o10.getPayments().add(pay4);

        Order o11 = new Order(1500.00,
            600.00, "SOD", c08);
        o11.getPayments().add(pay2);

        Order o12 = new Order(2500.00,
            0.00, "SOD", c25);
        o12.getPayments().add(pay1);

        agentService.save(a01);
        agentService.save(a02);
        agentService.save(a03);
        agentService.save(a04);
        agentService.save(a05);
        agentService.save(a06);
        agentService.save(a07);
        agentService.save(a08);
        agentService.save(a09);
        agentService.save(a10);
        agentService.save(a11);
        agentService.save(a12);

        customerService.save(c01);
        customerService.save(c02);
        customerService.save(c03);
        customerService.save(c04);
        customerService.save(c05);
        customerService.save(c06);
        customerService.save(c07);
        customerService.save(c08);
        customerService.save(c09);
        customerService.save(c10);
        customerService.save(c11);
        customerService.save(c12);
        customerService.save(c13);
        customerService.save(c14);
        customerService.save(c15);
        customerService.save(c16);
        customerService.save(c17);
        customerService.save(c18);
        customerService.save(c19);
        customerService.save(c20);
        customerService.save(c21);
        customerService.save(c22);
        customerService.save(c23);
        customerService.save(c24);
        customerService.save(c25);

        orderService.save(o01);
        orderService.save(o02);
        orderService.save(o03);
        orderService.save(o04);
        orderService.save(o05);
        orderService.save(o06);
        orderService.save(o07);
        orderService.save(o08);
        orderService.save(o09);
        orderService.save(o10);
        orderService.save(o11);
        orderService.save(o12);
    }
}