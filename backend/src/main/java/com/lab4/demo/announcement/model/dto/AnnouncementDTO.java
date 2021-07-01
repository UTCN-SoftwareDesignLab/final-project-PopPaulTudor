package com.lab4.demo.announcement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {

    private Long id;
    private String title;
    private String subject;
    private String content;
    private Long expeditorId;
    private Long gatherId;
    private String date;
}
