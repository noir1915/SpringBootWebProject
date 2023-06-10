package net.noir1915.converters;

import lombok.RequiredArgsConstructor;
import net.noir1915.dto.OrderItemDto;
import net.noir1915.model.OrderItem;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    private final ProductConverter productConverter;

    public OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        var dto = new OrderItemDto();
        dto.setId(orderItem.getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setPricePerProduct(orderItem.getPricePerProduct());
        dto.setProductDto(productConverter.entityToDto(orderItem.getProduct()));
        return dto;
    }

    public OrderItem mapToOrderItemEntity(OrderItemDto dto) {
        var orderItem = new OrderItem();
        orderItem.setId(dto.getId());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPrice(dto.getPrice());
        orderItem.setPricePerProduct(dto.getPricePerProduct());
        orderItem.setProduct(productConverter.toEntity(dto.getProductDto()));
        return orderItem;
    }
}