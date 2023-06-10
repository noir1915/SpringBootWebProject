package service;


import net.noir1915.dto.PaymentDto;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.Payment;
import net.noir1915.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    public void createTest() {
        var dto = new PaymentDto();
        dto.setOrderId(1L);
        dto.setCustomerId(2L);
        dto.setAmount(new BigDecimal("13000.50"));

        var payment = paymentService.create(dto);

        assertAll(
                () -> assertEquals(1, payment.getOrder().getId()),
                () -> assertEquals(2, payment.getCustomer().getId()),
                () -> assertEquals(new BigDecimal("13000.50"), payment.getAmount())
        );

    }

    @Test
    public void getAllTest() {
        int count = paymentService.getAll().size();

        assertTrue(count > 0);
    }

    @Test
    public void removeTest() {
        Payment payment = paymentService.getById(3L);

        assertAll(
                () -> assertEquals(3L, payment.getId()),
                () -> assertEquals(3L, payment.getCustomer().getId()),
                () -> assertEquals(3L, payment.getOrder().getId())
        );
        paymentService.remove(payment.getId());

        assertThrows(ResourceNotFoundException.class,
                () -> paymentService.getById(3L));
    }

    @Test
    public void updateTest() {
        PaymentDto dto = new PaymentDto();
        dto.setId(1L);
        dto.setOrderId(2L);
        dto.setCustomerId(2L);
        dto.setAmount(new BigDecimal("100.0"));

        Payment oldPayment = paymentService.getById(1L);

        assertAll(
                () -> assertEquals(1L, oldPayment.getId()),
                () -> assertEquals(1L, oldPayment.getCustomer().getId()),
                () -> assertEquals(1L, oldPayment.getOrder().getId()),
                () -> assertEquals(new BigDecimal("12000.02"), oldPayment.getAmount())
        );

        Payment updatedPayment = paymentService.update(dto);

        assertAll(
                () -> assertEquals(1L, updatedPayment.getId()),
                () -> assertEquals(2L, updatedPayment.getCustomer().getId()),
                () -> assertEquals(2L, updatedPayment.getOrder().getId()),
                () -> assertEquals(new BigDecimal("100.0"), updatedPayment.getAmount())
        );


    }


}
