package com.whereismyhome.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
