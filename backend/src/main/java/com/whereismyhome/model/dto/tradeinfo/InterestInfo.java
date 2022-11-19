package com.whereismyhome.model.dto.tradeinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class InterestInfo {
	@NonNull
	private String userid;
	private String sido;
	private String gugun;
	private String dong;	
}
