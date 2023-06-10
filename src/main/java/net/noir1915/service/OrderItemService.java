package net.noir1915.service;

import lombok.RequiredArgsConstructor;
import net.noir1915.converters.OrderItemConverter;
import net.noir1915.dto.OrderItemDto;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.OrderItem;
import net.noir1915.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemConverter orderItemConverter;
    private final OrderItemRepository orderItemRepository;

    public OrderItemDto getById(Long id) {
        return orderItemConverter.mapToOrderItemDto(orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("OrderItem with id = %d not found", id))));
    }

    public List<OrderItemDto> getAll() {
        return orderItemRepository.findAll().stream().map(orderItemConverter::mapToOrderItemDto).collect(Collectors.toList());
    }

    public OrderItem create(OrderItemDto dto) {
        return orderItemRepository.save(orderItemConverter.mapToOrderItemEntity(dto));
    }

    @Transactional
    public void remove(Long id) {
        orderItemRepository.delete(orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("OrderItem with id = %d not found", id))));
    }

    @Transactional
    public void update(OrderItemDto dto) {
        OrderItem orderItem = orderItemRepository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException(String.format("OrderItem with id = %d not found", dto.getId())));
        orderItem.setId(dto.getId());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPrice(dto.getPrice());
        orderItem.setPricePerProduct(dto.getPricePerProduct());
        orderItemRepository.save(orderItem);
    }
}