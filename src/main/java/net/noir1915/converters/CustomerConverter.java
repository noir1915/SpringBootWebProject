package net.noir1915.converters;

import net.noir1915.dto.CustomerDto;
import net.noir1915.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class CustomerConverter {

    public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        if (Objects.nonNull(dto.getEmail())) customer.setEmail(dto.getEmail());
        if (Objects.nonNull(dto.getPhone())) customer.setPhone(dto.getPhone());
        return customer;
    }

    public CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        return customerDto;
    }
}
