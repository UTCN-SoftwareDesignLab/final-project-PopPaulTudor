package com.lab4.demo.announcement;


import com.lab4.demo.announcement.model.dto.AnnouncementDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(ANNOUNCEMENTS)
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    public List<AnnouncementDTO> allAnnouncements() {
        return announcementService.findAll();
    }


    @PostMapping
    public AnnouncementDTO create(@RequestBody AnnouncementDTO dto) {
        return announcementService.create(dto);
    }

    @GetMapping(ENTITY)
    public List<AnnouncementDTO> allAnnouncementsFromGatherId(@PathVariable Long id) {
        return announcementService.findAllByGatherId(id);
    }


    @PutMapping(ENTITY)
    public AnnouncementDTO edit(@PathVariable Long id, @RequestBody AnnouncementDTO dto) {
        return announcementService.edit(id, dto);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        announcementService.delete(id);
    }



}
