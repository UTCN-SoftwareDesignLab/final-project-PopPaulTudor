package com.lab4.demo.announcement;

import com.lab4.demo.announcement.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> getAnnouncementsByGatherId(long gatherId);

}
