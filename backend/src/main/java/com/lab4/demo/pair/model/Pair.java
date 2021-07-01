package com.lab4.demo.pair.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Pair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long gatherId;

    @Column
    private Long userId;

    @Column
    private String userRole;

    @Column
    private Long points;

}
