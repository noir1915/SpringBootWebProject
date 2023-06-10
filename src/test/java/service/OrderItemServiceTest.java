package service;

import net.noir1915.dto.OrderItemDto;
import net.noir1915.dto.ProductDto;
import net.noir1915.exception.ResourceNotFoundException;
import net.noir1915.model.OrderItem;
import net.noir1915.service.OrderItemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderItemServiceTest {

    @Autowired
    private OrderItemService orderItemService;


    @Test
    public void getOrderItemByIdTest() {

        OrderItemDto orderItemDto = orderItemService.getById(2L);
        assertEquals(BigDecimal.valueOf(300000), orderItemDto.getPrice());
    }

    @Test
    public void getAllOrderItemTest() {

        assertEquals(3, orderItemService.getAll().size());
    }

    @Test
    public void createOrderItemTest() {

        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setProductTitle("Cool product");
        productDto.setProductType("NEW");
        productDto.setPreparation("NOT_DONE");
        productDto.setMaterialId(2L);
        productDto.setEndDate(LocalDateTime.now());

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(1L);
        orderItemDto.setQuantity(2);
        orderItemDto.setPrice(BigDecimal.valueOf(200000L));
        orderItemDto.setPricePerProduct(BigDecimal.valueOf(300000L));
        orderItemDto.setProductDto(productDto);
        OrderItem orderItem = orderItemService.create(orderItemDto);
        assertEquals(BigDecimal.valueOf(200000L), orderItem.getPrice());
    }


    @Test
    public void updateTest() {
        OrderItemDto dto = new OrderItemDto();
        dto.setId(1L);
        dto.setQuantity(2);
        dto.setPrice(BigDecimal.valueOf(355000));
        dto.setPricePerProduct(BigDecimal.valueOf(300000L));
        dto.setProductDto(new ProductDto());

        OrderItemDto oldOrderItem = orderItemService.getById(1L);

        assertAll(
                () -> assertEquals(1L, oldOrderItem.getId()),
                () -> assertEquals(BigDecimal.valueOf(200000), oldOrderItem.getPrice())
        );

        orderItemService.update(dto);
        OrderItemDto updateOrderItem = orderItemService.getById(1L);

        assertAll(
                () -> assertEquals(1L, updateOrderItem.getId()),
                () -> assertEquals(BigDecimal.valueOf(355000), updateOrderItem.getPrice())
        );
    }


    @Test
    public void removeTest() {
        OrderItemDto orderItemDto = orderItemService.getById(1L);
        assertEquals(1L, orderItemDto.getId());
        orderItemService.remove(orderItemDto.getId());
        assertThrows(ResourceNotFoundException.class, () -> orderItemService.getById(1L));
    }

}