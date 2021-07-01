package com.lab4.demo.pair.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PairDTO {

    private Long id;
    private Long gatherId;
    private Long userId;
    private String userRole;
}
