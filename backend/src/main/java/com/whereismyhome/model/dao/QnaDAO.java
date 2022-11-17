package com.whereismyhome.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.QnaInfo;

@Mapper
public interface QnaDAO {
	int insertArticleInfo(QnaInfo qnaInfo);
	int insertArticleContent(QnaInfo qnaInfo);
	List<QnaInfo> selectArticles(Map map);
	QnaInfo selectArticle(int articleNo);
	int deleteArticle(int articleNo);
	int updateArticle(QnaInfo qnaInfo);
	int increaseViewCount(int articleNo);
}
