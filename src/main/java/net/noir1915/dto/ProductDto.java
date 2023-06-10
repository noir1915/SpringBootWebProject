package net.noir1915.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String productTitle;
    private String productType;
    private boolean isProgramWritten;
    private Long materialId;
    private LocalDateTime endDate;
    private String preparation;
    private Long technologistId;
}
