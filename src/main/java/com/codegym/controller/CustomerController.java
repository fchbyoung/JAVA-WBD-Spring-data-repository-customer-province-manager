package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PropertySource("classpath:upload_file.properties")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ModelAndView listCustomers() {
        Iterable<Customer> customerList = customerService.findAll();

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customerList);
        return modelAndView;
    }


    @GetMapping("/customer/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create-customer");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/customer/add")
    public ModelAndView saveCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);

        ModelAndView modelAndView = new ModelAndView("/customer/create-customer");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "created.");
        return modelAndView;
    }

    @GetMapping("search")
    public ModelAndView searchCustomer(@RequestParam("name") String name) {
        Iterable<Customer> customers = customerService.findByName(name);
        ModelAndView modelAndView = new ModelAndView("/customer/search");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit-customer");
            modelAndView.addObject("customer", customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/customer/error-404");
            return modelAndView;
        }
    }

    @PostMapping("edit")
    public ModelAndView editCustomer(@ModelAttribute("customer")Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit-customer");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable("id") Long id) {
        customerService.delete(id);
        Iterable<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

}
