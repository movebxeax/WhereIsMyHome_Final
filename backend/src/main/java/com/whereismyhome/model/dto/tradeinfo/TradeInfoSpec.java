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
public class TradeInfoSpec {
	//hd.aptcode, hi.aptname, hi.dongcode, hd.area, hd.dealamount as price, hd.dealyear, hd.dealmonth
	private String aptCode;
	private String aptName;
	private String buildYear;
	private String dongCode;
	private Double lat;
	private Double lng;
	private List<DetailInfo> details;
}
