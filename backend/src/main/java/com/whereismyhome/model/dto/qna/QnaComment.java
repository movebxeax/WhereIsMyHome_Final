package com.whereismyhome.model.dto.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QnaComment {
	String author;
	String content;
	String date;
	int likeCount;
}
