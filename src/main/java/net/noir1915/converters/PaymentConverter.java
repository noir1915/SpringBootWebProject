package net.noir1915.converters;

import lombok.RequiredArgsConstructor;
import net.noir1915.dto.PaymentDto;
import net.noir1915.model.Payment;
import net.noir1915.service.CustomerService;
import net.noir1915.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class PaymentConverter {
    private final OrderService orderService;
    private final CustomerService customerService;

    public Payment toEntity(PaymentDto dto) {
        var payment = new Payment();
        if (Objects.nonNull(dto.getId())) payment.setId(dto.getId());
        payment.setOrder(orderService.getById(dto.getOrderId()));
        payment.setCustomer(customerService.getCustomerById(dto.getCustomerId()));
        payment.setAmount(dto.getAmount());
        return payment;
    }

    public PaymentDto toDto(Payment payment) {
        var dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setOrderId(payment.getOrder().getId());
        dto.setCustomerId(payment.getCustomer().getId());
        dto.setAmount(payment.getAmount());
        return dto;

    }
}
