package com.whereismyhome.model.service;

import java.util.List;
import java.util.Map;

import com.whereismyhome.model.dto.QnaInfo;

public interface QnaService {
	public boolean writeQnaArticle(QnaInfo qnaInfo);
	public List<QnaInfo> getQnaArticleList(Map map);
	public QnaInfo getQnaArticle(int articleNo);
	public boolean modifyQnaArticle(QnaInfo qnaInfo);
	public boolean deleteQnaArticle(int articleNo);
	public boolean updateQnaArticleViewCount(int articleNo);
}
