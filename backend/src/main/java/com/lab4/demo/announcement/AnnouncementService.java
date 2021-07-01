package com.lab4.demo.announcement;


import com.lab4.demo.announcement.model.Announcement;
import com.lab4.demo.announcement.model.dto.AnnouncementDTO;

import com.lab4.demo.pair.PairRepository;
import com.lab4.demo.pair.model.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.lab4.demo.utills.CalendarUtills.getStringOfMillis;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final PairRepository pairRepository;
    private final AnnouncementMapper announcementMapper;

    private Announcement findById(Long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found: " + id));
    }

    public List<AnnouncementDTO> findAll() {
        return announcementRepository.findAll().stream()
                .map(announcementMapper::toDto)
                .collect(Collectors.toList());
    }

    public AnnouncementDTO create(AnnouncementDTO item) {
        Pair pair =  pairRepository.getPairByUserIdAndAndGatherId(item.getExpeditorId(), item.getGatherId());
        pair.setPoints(pair.getPoints() + 5);
        pairRepository.save(pair);
        item.setDate(getStringOfMillis(Calendar.getInstance().getTimeInMillis()));

        return announcementMapper.toDto(announcementRepository.save(
                announcementMapper.fromDto(item)
        ));
    }

    public AnnouncementDTO edit(Long id, AnnouncementDTO dto) {
        Announcement actItem = findById(id);

        return announcementMapper.toDto(
                announcementRepository.save(actItem)
        );
    }

    public AnnouncementDTO get(Long id) {
        return announcementMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        announcementRepository.deleteById(id);
    }

    public List<AnnouncementDTO> findAllByGatherId(Long gatherId) {
        return announcementRepository.getAnnouncementsByGatherId(gatherId).stream()
                .map(announcementMapper::toDto)
                .collect(Collectors.toList());
    }
}
