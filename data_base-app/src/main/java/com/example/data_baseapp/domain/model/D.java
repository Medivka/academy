package com.example.data_baseapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sacuta V.A.
 */

@Data
@Entity
@Table(name = "d_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class D {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String isAlone;
    private String name;
    private Integer number;
    private Integer cinema;
    private String hero;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "b_and_d_table",
            joinColumns = @JoinColumn(name = "id_d"),
            inverseJoinColumns = @JoinColumn(name = "id_b"))
    private List<B> courses = new LinkedList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "single_id")
    private Single single;
}
