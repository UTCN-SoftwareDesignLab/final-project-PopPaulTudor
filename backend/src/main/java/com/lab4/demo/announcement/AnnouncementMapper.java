package com.lab4.demo.announcement;


import com.lab4.demo.announcement.model.Announcement;
import com.lab4.demo.announcement.model.dto.AnnouncementDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    AnnouncementDTO toDto(Announcement announcement);

    Announcement fromDto(AnnouncementDTO dto);

}
