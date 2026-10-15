package AutoCareHub.controller;

import AutoCareHub.entity.Customer;
import AutoCareHub.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")

@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://autocarehub12.netlify.app"
})

public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    // Add customer
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // Update customer
    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        Customer existingCustomer =
                customerRepository.findById(id).orElse(null);

        if (existingCustomer == null) {
            return null;
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());

        return customerRepository.save(existingCustomer);
    }

    // Delete customer
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        customerRepository.deleteById(id);

        return "Customer deleted successfully";
    }
}