package com.whereismyhome.model.dto.notice;

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
public class NoticeInfo {
	@NonNull
	private int no;
	
	private String title;
	private String content;
	private String author;
}
