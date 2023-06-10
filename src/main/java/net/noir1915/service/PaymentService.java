package net.noir1915.service;

import lombok.RequiredArgsConstructor;
import net.noir1915.converters.PaymentConverter;
import net.noir1915.dto.PaymentDto;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.Payment;
import net.noir1915.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentConverter paymentConverter;

    public Payment create(PaymentDto dto) {
        return paymentRepository.save(paymentConverter.toEntity(dto));
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Payment getById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Payment with id = %d not found", id)));
    }

    @Transactional
    public void remove(Long id) {
        paymentRepository.delete(paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Payment with id = %d not found", id))));
    }
    @Transactional
    public Payment update(PaymentDto dto) {
        return paymentRepository.save(paymentConverter.toEntity(dto));
    }




}
