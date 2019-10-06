package com.soen.riskgame.module.core.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddContinent {
	
	private String continentName;
	
	private int controlValue;

}
