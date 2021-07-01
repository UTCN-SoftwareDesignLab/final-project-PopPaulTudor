package com.lab4.demo.gather;


import com.lab4.demo.gather.model.Gather;
import com.lab4.demo.gather.model.dto.GatherDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GatherMapper {

    GatherDTO toDto(Gather gather);

    Gather fromDto(GatherDTO dto);
}
