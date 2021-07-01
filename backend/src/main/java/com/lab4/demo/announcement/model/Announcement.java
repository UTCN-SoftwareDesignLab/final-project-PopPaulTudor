package com.lab4.demo.announcement.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Announcement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String subject;

    @Column
    private String content;

    @Column
    private Long expeditorId;

    @Column
    private Long gatherId;

    @Column
    private String dateSent;
}
