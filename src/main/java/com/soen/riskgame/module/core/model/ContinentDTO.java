package com.soen.riskgame.module.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContinentDTO {

    private Long id;

    private String name;

    private Integer controlValue;

    private String color;

}
