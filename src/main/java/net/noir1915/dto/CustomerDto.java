package net.noir1915.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;

}
