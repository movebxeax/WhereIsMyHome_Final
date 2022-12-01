package com.whereismyhome.model.dto.tradeinfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(title = "Address Info Schema")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfo {
	String address;
	String dongcode;
	double lat;
	double lng;
}
