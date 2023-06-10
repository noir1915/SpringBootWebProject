package net.noir1915.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.noir1915.converters.PaymentConverter;
import net.noir1915.dto.PaymentDto;
import net.noir1915.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Payment controller", description = "Операции по работе с платежами")
@RestController
@RequestMapping("api/v1/payments")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentConverter paymentConverter;

    @Operation(summary = "Добавление платежа")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PaymentDto addPayment(@RequestBody PaymentDto dto) {
        return paymentConverter.toDto(paymentService.create(dto));
    }

    @Operation(summary = "Получить все платежи")
    @GetMapping
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAll().stream()
                .map(paymentConverter::toDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Удалить платеж по id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePayment(@PathVariable Long id) {
        paymentService.remove(id);
    }
}
