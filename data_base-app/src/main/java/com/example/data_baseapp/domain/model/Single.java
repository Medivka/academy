package com.example.data_baseapp.domain.model;

import com.example.data_baseapp.domain.enums.E;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Sacuta V.A.
 */

@Data
@Entity
public class Single {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private E e;

    @OneToOne(mappedBy = "single")
    private D d;

    public Single() {
    }
}
