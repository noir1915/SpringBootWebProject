package net.noir1915.service;

import lombok.RequiredArgsConstructor;
import net.noir1915.converters.CustomerConverter;
import net.noir1915.converters.OrderConverter;
import net.noir1915.dto.CustomerDto;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.Customer;
import net.noir1915.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final OrderConverter orderConverter;

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with id: %d not found", id)));
    }

    public Customer create(CustomerDto customerDto) {
        return customerRepository.save(customerConverter.toEntity(customerDto));
    }
    @Transactional
    public void remove(Long id) {
        customerRepository.delete(customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with id = %d not found", id))));
    }

    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream().map(customerConverter::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void update(CustomerDto dto) {
        customerRepository.save(customerConverter.toEntity(dto));
    }

}