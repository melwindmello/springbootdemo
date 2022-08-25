package com.melit.springbootdemo.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "officers")
public class Officer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column(nullable = false, name = "first_name")
    private String first;

    @Column(nullable = false, name = "last_name")
    private String last;


    public Officer(Rank rank, String first, String last) {
        this.rank = rank;
        this.first = first;
        this.last = last;
    }

}
