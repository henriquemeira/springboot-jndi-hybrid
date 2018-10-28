package co.h2a.sandbox.springbootjndi;

import co.h2a.sandbox.springbootjndi.data.Customer;
import co.h2a.sandbox.springbootjndi.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public Iterable<Customer> customers() {
        return customerRepository.findAll();
    }

}