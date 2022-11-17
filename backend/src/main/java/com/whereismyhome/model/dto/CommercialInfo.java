package com.whereismyhome.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommercialInfo {
	private String name;
	private String categoryCode;
	private String categoryName;
	private double lng;
	private double lat;
}
