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

@Entity
@Data
@Table(name = "b_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class B {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer height;
    private String isDead;
    private String address;
    private String phone;
    private Integer amount;

    @OneToMany(mappedBy = "b", fetch = FetchType.LAZY)
    private List<A> aList = new LinkedList<>();

    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(name = "b_and_d_table",
            joinColumns = @JoinColumn(name = "id_b"),
            inverseJoinColumns = @JoinColumn(name = "id_d"))
    private List<D> dList = new LinkedList<>();
}
