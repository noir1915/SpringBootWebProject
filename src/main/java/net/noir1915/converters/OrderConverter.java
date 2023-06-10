package net.noir1915.converters;

import lombok.RequiredArgsConstructor;
import net.noir1915.dto.OrderDto;
import net.noir1915.model.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final CustomerConverter customerConverter;
    private final OrderItemConverter orderItemConverter;

    public OrderDto mapToOrderDto(Order order) {
        var dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCreated(order.getCreated());
        dto.setModified(order.getModified());
        dto.setTotalCost(order.getTotalCost());
        dto.setCompletion(order.getCompletion());
        dto.setOrderTitle(order.getOrderTitle());
        dto.setComplected(order.isComplected());

        if (Objects.nonNull(order.getCustomer())) {
            dto.setCustomerDto(customerConverter.toDto(order.getCustomer()));
        }
        if (Objects.nonNull(order.getOrderItems())) {
            dto.setOrderItemDtos(order.getOrderItems().stream().map(orderItemConverter::mapToOrderItemDto).collect(Collectors.toList()));
        }

        return dto;
    }

    public Order mapToOrderEntity(OrderDto orderDto) {
        var order = new Order();
        order.setId(orderDto.getId());
        order.setCreated(orderDto.getCreated());
        order.setModified(orderDto.getModified());
        order.setTotalCost(orderDto.getTotalCost());
        order.setCompletion(orderDto.getCompletion());
        order.setOrderTitle(orderDto.getOrderTitle());
        order.setComplected(orderDto.isComplected());

        if (Objects.nonNull(orderDto.getCustomerDto())) {
            order.setCustomer(customerConverter.toEntity(orderDto.getCustomerDto()));
        }
        if (Objects.nonNull(orderDto.getOrderItemDtos())) {
            order.setOrderItems(orderDto.getOrderItemDtos().stream().map(orderItemConverter::mapToOrderItemEntity).collect(Collectors.toList()));
        }

        return order;
    }
}