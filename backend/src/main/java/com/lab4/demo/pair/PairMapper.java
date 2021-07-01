package com.lab4.demo.pair;

import com.lab4.demo.pair.model.Pair;
import com.lab4.demo.pair.model.dto.PairDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PairMapper {

    PairDTO toDto(Pair pair);
    Pair fromDto(PairDTO dto);
}
