package com.whereismyhome.model.dto.tradeinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GugunInfo {
	public String gugunCode;
	public String sidoName;
	public String gugunName;
	public Double lat;
	public Double lng;
}
