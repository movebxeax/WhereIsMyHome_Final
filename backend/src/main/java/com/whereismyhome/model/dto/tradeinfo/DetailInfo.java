package com.whereismyhome.model.dto.tradeinfo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailInfo {
	private double area;
	private List<PriceInfo> priceInfoList;
}
