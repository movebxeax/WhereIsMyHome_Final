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
public class TradeInfo {
	private String aptCode;
	private String aptName;
	private String dongCode;
	private String buildYear;
	private List<PriceInfo> details;
	
}
