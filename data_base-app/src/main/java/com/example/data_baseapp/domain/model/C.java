package com.example.data_baseapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Sacuta V.A.
 */

@Data
@Entity
@Table(name = "c_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class C {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String lastname;
    private Integer age;
    private Integer memory;
    private String isFast;
    private String car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a")
    private A a;

}
