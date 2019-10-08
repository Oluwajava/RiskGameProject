package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorderDTO {

    private Long countryId;

    private List<Long> adjacentCountries;

}