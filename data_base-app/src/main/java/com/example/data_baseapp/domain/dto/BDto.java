package com.example.data_baseapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sacuta V.A.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BDto {
    private Integer id;
    private Integer height;
    private String isDead;
    private String address;
    private String phone;
    private Integer amount;
}
