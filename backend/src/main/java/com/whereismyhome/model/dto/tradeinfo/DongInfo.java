package com.whereismyhome.model.dto.tradeinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DongInfo {
	public String dongName;
	public String dongCode;
	public Double lat;
	public Double lng;
	public Integer aptCount;
}
