package com.lab4.demo.gather;

import com.lab4.demo.gather.model.Gather;
import com.lab4.demo.gather.model.dto.GatherDTO;
import com.lab4.demo.gather.model.dto.GatherPairDTO;
import com.lab4.demo.pair.PairRepository;
import com.lab4.demo.pair.model.Pair;
import com.lab4.demo.pair.model.dto.PairDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.lab4.demo.utills.CalendarUtills.getStringOfMillis;

@Service
@RequiredArgsConstructor
public class GatherService {

    private final GatherRepository gatherRepository;
    private final PairRepository pairRepository;
    private final GatherMapper gatherMapper;

    private Gather findById(Long id) {
        return gatherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found: " + id));
    }

    public List<GatherDTO> findAll() {
        return gatherRepository.findAll().stream()
                .map(gatherMapper::toDto)
                .collect(Collectors.toList());
    }

    public GatherDTO create(GatherPairDTO dto) {
        GatherDTO gatherDTO = GatherDTO.builder()
                .subject(dto.getSubject())
                .title(dto.getTitle())
                .build();
        gatherDTO.setDateCreated(getStringOfMillis(Calendar.getInstance().getTimeInMillis()));
        GatherDTO dto1 = gatherMapper.toDto(gatherRepository.save(
                gatherMapper.fromDto(gatherDTO)
        ));
        pairRepository.save(Pair.builder()
                .userRole("Admin")
                .gatherId(dto1.getId())
                .userId(dto.getId())
                .points(0L)
                .build());
        return dto1;
    }

    public GatherDTO edit(Long id, GatherDTO dto) {
        Gather actItem = findById(id);


        return gatherMapper.toDto(
                gatherRepository.save(actItem)
        );
    }

    public GatherDTO get(Long id) {
        return gatherMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        gatherRepository.deleteById(id);
    }

    public List<GatherDTO> findAllContainingUserId(long id) {
        return pairRepository.getPairsByUserId(id).stream()
                .map((pair) -> findById(pair.getGatherId()))
                .map(gatherMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<GatherDTO> findAllNotContainingUserId(long id) {
        List<Gather> gathers = gatherRepository.findAll();
        List<Long> joinedGathersIds = findAllContainingUserId(id)
                .stream().map(GatherDTO::getId).collect(Collectors.toList());

        return gathers.stream().filter((gather -> !joinedGathersIds.contains(gather.getId())))
                .map(gatherMapper::toDto)
                .collect(Collectors.toList());
    }

    public void joinGroup(PairDTO dto) {
        pairRepository.save(Pair.builder()
                .points(0L)
                .userId(dto.getUserId())
                .userRole("USER")
                .gatherId(dto.getGatherId())
                .build());

    }
}
