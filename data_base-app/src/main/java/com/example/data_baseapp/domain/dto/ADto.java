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
public class ADto {
    private Integer id;
    private String name;
    private String password;
    private String enable;
    private String tree;
    private String apple;

}
