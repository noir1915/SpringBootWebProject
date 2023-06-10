package service;

import net.noir1915.dto.CustomerDto;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.Customer;
import net.noir1915.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void getCustomerGetByIdTest() {
        Customer customer = customerService.getCustomerById(1L);
        assertEquals("Ivan", customer.getName());
    }

    @Test
    public void getAllCustomerTest() {
        Integer customerCount = customerService.getAll().size();
        assertEquals(3, customerCount);
    }

    @Test
    public void createCustomerTest() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail("newCustomer@mail.ru");
        customerDto.setName("newCustomerName");
        customerDto.setPhone("+79999222211");
        Customer customer = customerService.create(customerDto);
        assertEquals("newCustomerName", customer.getName());
    }

    @Test
    public void removeCustomerTest() {
        Customer customer = customerService.getCustomerById(4L);
        assertEquals("newCustomerName", customer.getName());
        customerService.remove(customer.getId());
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(4L));
    }

    @Test
    public void updateCustomerTest() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("John");
        customerDto.setEmail("Snow@mail.ru");
        customerDto.setPhone("+7986365471");

        Customer oldCustomer = customerService.getCustomerById(1L);
        assertAll(
                () -> assertEquals(1L, oldCustomer.getId()),
                () -> assertEquals("Ivan", oldCustomer.getName()));

        customerService.update(customerDto);
        Customer updateCustomerDto = customerService.getCustomerById(1L);

        assertAll(
                () -> assertEquals(1L, updateCustomerDto.getId()),
                () -> assertEquals("John", updateCustomerDto.getName()));
    }
}
