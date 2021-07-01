package com.lab4.demo.gather;

import com.lab4.demo.gather.model.dto.GatherDTO;
import com.lab4.demo.gather.model.dto.GatherPairDTO;
import com.lab4.demo.pair.model.dto.PairDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Join;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(GATHERS)
@RequiredArgsConstructor
public class GatherController {

    private final GatherService gatherService;

    @GetMapping
    public List<GatherDTO> allGroups() {
        return gatherService.findAll();
    }


    @PostMapping
    public GatherDTO create(@RequestBody GatherPairDTO dto) {
        return gatherService.create(dto);
    }


    @PutMapping(ENTITY)
    public GatherDTO edit(@PathVariable Long id, @RequestBody GatherDTO dto) {
        return gatherService.edit(id, dto);
    }

    @GetMapping(ENTITY)
    public List<GatherDTO> allGathersContainingUserId(@PathVariable long id) {
        return gatherService.findAllContainingUserId(id);
    }

    @GetMapping(NOT + ENTITY)
    public List<GatherDTO> allGathersNotContainingUserId(@PathVariable long id) {
        return gatherService.findAllNotContainingUserId(id);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        gatherService.delete(id);
    }

    @PostMapping(JOIN)
    public void joinGroup(@RequestBody PairDTO dto) {
        gatherService.joinGroup(dto);
    }



}
