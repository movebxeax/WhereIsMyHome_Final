package com.whereismyhome.model.dto.tradeinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfo {
	private String price;
	private String dealYear;
	private String dealMonth;
}
