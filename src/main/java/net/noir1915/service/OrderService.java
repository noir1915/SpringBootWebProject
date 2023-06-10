package net.noir1915.service;

import lombok.RequiredArgsConstructor;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.Order;
import net.noir1915.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Order with id = %d not found", id)));
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order create(Order order) {
        order.onCreate();
        return orderRepository.save(order);
    }

    @Transactional
    public Order update(Order order) {
        order.onUpdate();
       return orderRepository.save(order);
    }
}
