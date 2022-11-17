package com.whereismyhome.model.dto.tradeinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeInfo {
	//hd.aptcode, hi.aptname, hi.dongcode, hd.area, hd.dealamount as price, hd.dealyear, hd.dealmonth
	private String aptCode;
	private String aptName;
	private String dongCode;
	private double area;
	private String price;
	private String dealyear;
	private String dealmonth;
}
