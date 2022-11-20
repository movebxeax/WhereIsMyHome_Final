package com.whereismyhome.model.service.qna;

import java.util.List;
import java.util.Map;

import com.whereismyhome.model.dto.qna.QnaInfo;

public interface QnaService {
	public boolean writeQnaArticle(QnaInfo qnaInfo);
	public List<QnaInfo> getQnaArticleList(Map<String, Object> map);
	public QnaInfo getQnaArticle(int articleNo);
	public boolean modifyQnaArticle(QnaInfo qnaInfo);
	public boolean deleteQnaArticle(int articleNo);
	public boolean updateQnaArticleViewCount(int articleNo);
}
