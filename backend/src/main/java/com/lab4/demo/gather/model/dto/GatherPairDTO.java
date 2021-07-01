package com.lab4.demo.gather.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatherPairDTO {

    private Long id;
    private String title;
    private String subject;
}
