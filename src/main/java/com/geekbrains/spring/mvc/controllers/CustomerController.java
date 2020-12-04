package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Customer;
import com.geekbrains.spring.mvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// root: http://localhost:8989/app/customers

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    // /add/customers
    @GetMapping
    public String showAll(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);

        // WEB-INF/templates/customers.html
        return "customers";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "customer_add_form";
    }

//    @PostMapping("/add")
//    public String addCustomer(
//            @RequestParam(name = "id", required = false) Long id,
//            @RequestParam String name,
//            @RequestParam String email,
//            @RequestParam String phone
//    ) {
//        Customer customer = new Customer(id, name, email, phone);
//        customerService.saveOrUpdate(customer);
//        return "redirect:/customers/";
//    }

    @PostMapping("/add")
    public String addCustomer(
            @ModelAttribute Customer newCustomer
    ) {
        customerService.saveOrUpdate(newCustomer);
        return "redirect:/customers/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id, Model model
    ) {
        model.addAttribute("customer", customerService.findById(id));
        return "customer_edit_form";
    }

    @PostMapping("/edit")
    public String modifyCustomer(
            @ModelAttribute Customer modifiedCustomer
    ) {
        customerService.saveOrUpdate(modifiedCustomer);
        return "redirect:/customers/";
    }

    @GetMapping(value = "/json/{id}")
    public HttpEntity showJsonCustomer(
            @PathVariable Long id
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Customer> entity = new HttpEntity<>(customerService.findById(id), headers);
        return entity;
    }

    @GetMapping(value = "/customer/{id}")
    public String showCustomer(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute("customer", customerService.findById(id));
        return "customer";
    }
}
