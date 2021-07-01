package com.lab4.demo.pair;

import com.lab4.demo.pair.model.dto.PairDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(PAIRS)
@RequiredArgsConstructor
public class PairController {

    private final PairService pairService;

    @GetMapping
    public List<PairDTO> allPairs() {
        return pairService.findAll();
    }


    @PostMapping
    public PairDTO create(@RequestBody PairDTO dto) {
        return pairService.create(dto);
    }


    @PutMapping(ENTITY)
    public PairDTO edit(@PathVariable Long id, @RequestBody PairDTO dto) {
        return pairService.edit(id, dto);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        pairService.delete(id);
    }

    @GetMapping(GATHER_ENTITY + ENTITY)
    public double getPointsFromGatherId(@PathVariable Long gatherId, @PathVariable Long id){
        return pairService.getPointsByIdFromGather(gatherId, id);
    }

    @GetMapping(ENTITY)
    public double getAllPoints(@PathVariable Long id){
        return pairService.getPointsById(id);
    }


}
