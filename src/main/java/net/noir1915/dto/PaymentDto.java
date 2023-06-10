package net.noir1915.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PaymentDto {
    private Long id;
    private Long orderId;
    private Long customerId;
    private BigDecimal amount;

}
