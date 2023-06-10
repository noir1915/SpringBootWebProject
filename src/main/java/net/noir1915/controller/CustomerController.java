package net.noir1915.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import net.noir1915.converters.CustomerConverter;
import net.noir1915.dto.CustomerDto;
import net.noir1915.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @GetMapping
    @Operation(summary = "To get all customers from database")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAll();
    }

    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        return customerConverter.toDto(customerService.create(customerDto));
    }


}
