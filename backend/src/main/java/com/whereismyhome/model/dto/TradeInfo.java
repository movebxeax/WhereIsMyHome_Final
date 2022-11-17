package com.whereismyhome.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeInfo {
	private int no;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String jibun;
	private String aptName;
	private String dongCode;
	private int floor;
	private int dealYear;
	private int dealMonth;
	private int dealAmount;
	private double lng;
	private double lat;
}
