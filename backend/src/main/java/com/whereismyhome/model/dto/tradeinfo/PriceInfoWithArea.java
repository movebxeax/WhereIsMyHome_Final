package com.whereismyhome.model.dto.tradeinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfoWithArea {
	private double area;
	private String price;
	private String dealYear;
	private String dealMonth;
}
