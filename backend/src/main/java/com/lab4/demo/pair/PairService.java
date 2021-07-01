package com.lab4.demo.pair;

import com.lab4.demo.pair.model.Pair;
import lombok.RequiredArgsConstructor;
import com.lab4.demo.pair.model.dto.PairDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PairService {

    private final PairRepository pairRepository;
    private final PairMapper pairMapper;


    private Pair findById(Long id) {
        return pairRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Match not found: " + id));
    }

    public List<PairDTO> findAll() {
        return pairRepository.findAll().stream()
                .map(pairMapper::toDto)
                .collect(Collectors.toList());
    }

    public PairDTO create(PairDTO dto) {
        return pairMapper.toDto(pairRepository.save(
                pairMapper.fromDto(dto)
        ));
    }

    public PairDTO edit(Long id, PairDTO dto) {
        Pair actItem = findById(id);

        return pairMapper.toDto(
                pairRepository.save(actItem)
        );
    }

    public PairDTO get(Long id) {
        return pairMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        pairRepository.deleteById(id);
    }


    public double getPointsByIdFromGather(Long gatherId, Long id) {
        return pairRepository.getPairByUserIdAndAndGatherId(id, gatherId).getPoints();
    }

    public double getPointsById(Long id) {
        return pairRepository.getPairsByUserId(id).stream().mapToDouble(Pair::getPoints).sum();

    }
}
