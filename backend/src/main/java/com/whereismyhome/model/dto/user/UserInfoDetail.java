package com.whereismyhome.model.dto.user;

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
public class UserInfoDetail {
	@NonNull
	private String userid;
	private String password;
	private String username;
	private String emailId;
	private String emailDomain;
	private String contact;
	private String baseAddress;
	private String specAddress;
}
