package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContinentDTO {

    private String name;

    private Long id;

    private String color;

    private Integer controlValue;

}
