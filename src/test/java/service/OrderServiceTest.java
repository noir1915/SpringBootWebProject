package service;


import net.noir1915.model.Customer;
import net.noir1915.model.Order;
import net.noir1915.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void creatTest() {
        Order order = new Order();
        Customer customer = new Customer();
        customer.setId(1L);
        order.setCustomer(customer);

        Order sevedOrder = orderService.create(order);

        assertTrue(Objects.nonNull(sevedOrder.getId()));
    }

    @Test
    public void getByIdTest() {
        Order o = orderService.getById(1L);

        assertAll(
                () -> assertEquals(1L, o.getId()),
                () -> assertEquals("авто", o.getOrderTitle()),
                () -> assertEquals(new BigDecimal("322123.23"), o.getTotalCost()),
                () -> assertEquals(1L, o.getCustomer().getId())
        );
    }

    @Test
    public void getAllTest() {
        int count = orderService.getAll().size();

        assertTrue(count > 0);
    }

    @Test
    public void updateTest() {
        Order order = orderService.getById(2L);

        assertAll(
                () -> assertEquals(2L, order.getId()),
                () -> assertEquals("квартира", order.getOrderTitle()),
                () -> assertTrue(order.isComplected())
        );

        order.setOrderTitle("updated title");
        order.setComplected(false);

        Order updatedOrder = orderService.update(order);
        assertAll(
                () -> assertEquals(2L, updatedOrder.getId()),
                () -> assertEquals("updated title", updatedOrder.getOrderTitle()),
                () -> assertFalse(updatedOrder.isComplected())
        );
    }

}