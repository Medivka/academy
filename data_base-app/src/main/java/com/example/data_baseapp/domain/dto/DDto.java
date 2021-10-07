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
public class DDto {
    private Integer id;
    private String isAlone;
    private String name;
    private Integer number;
    private Integer cinema;
    private String hero;
}
