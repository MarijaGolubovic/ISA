package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Survey {
    @Id
    @SequenceGenerator(
            name = "survey_sequence",
            sequenceName = "survey_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "survey_sequence"
    )
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
