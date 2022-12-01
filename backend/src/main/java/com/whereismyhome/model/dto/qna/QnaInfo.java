package com.whereismyhome.model.dto.qna;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Schema(title = "QnA Schema")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class QnaInfo {
	@Schema(title = "Article No", example = "1")
	int no;
	
	@Schema(title = "Article Title", example = "Sample Title")
	@NonNull
	String title;
	
	@Schema(title = "Article Content", example = "Sample Content. Hello world!")
	@NonNull
	String content;
	
	@Schema(title = "Article Author", example = "John Doe")
	@NonNull
	String author;
	
	@Schema(title = "Article Written Date", example = "YYYY-MM-DD hh:mm:ss")
	String date;
	
	@Schema(title = "Article View Count", example = "15")
	int viewCount;
	
	@Schema(title = "Article UUID", example = "0093f9f3-d125-4798-8419-bbbbe33fc1ed")
	String uuid;
	
	@Schema(title = "Article Like Count", example = "0")
	int likeCount;
	
	List<QnaComment> comments;
}
