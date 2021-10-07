package com.example.data_baseapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sacuta V.A.
 */
@Data
@Entity
@Table(name = "a_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class A {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private String enable;
    private String tree;
    private String apple;

    @OneToMany(mappedBy = "a", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private List<C> cList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "b")
    private B b;
}
