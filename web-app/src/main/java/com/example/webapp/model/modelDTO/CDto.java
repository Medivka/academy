package com.example.webapp.model.modelDTO;

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
public class CDto {
    private Integer id;
    private String name;
    private String lastname;
    private Integer age;
    private Integer memory;
    private String isFast;
    private String car;
}
