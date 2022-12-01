package com.whereismyhome.model.service.qna;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whereismyhome.model.dao.qna.QnaDAO;
import com.whereismyhome.model.dto.qna.QnaInfo;

@Service
public class QnaServiceImpl implements QnaService {
	@Autowired
	QnaDAO qnaDao;

	@Transactional
	@Override
	public boolean writeQnaArticle(QnaInfo qnaInfo) {
		return qnaDao.insertArticleInfo(qnaInfo) > 0 && qnaDao.insertArticleContent(qnaInfo) > 0;
	}

	@Transactional(readOnly = true)
	@Override
	public List<QnaInfo> getQnaArticleList(Map<String, Object> map) {
		return qnaDao.selectArticles(map);
	}

	@Transactional(readOnly = true)
	@Override
	public QnaInfo getQnaArticle(int articleNo) {
		return qnaDao.selectArticle(articleNo);
	}

	@Override
	public boolean modifyQnaArticle(QnaInfo qnaInfo) {
		return qnaDao.updateArticle(qnaInfo) > 0;
	}

	@Override
	public boolean deleteQnaArticle(int articleNo) {
		return qnaDao.deleteArticle(articleNo) > 0;
	}

	@Override
	public boolean updateQnaArticleViewCount(int articleNo) {
		return qnaDao.increaseViewCount(articleNo) > 0;
	}

	@Override
	public boolean writeQnaComment(Map<String, Object> map) {
		return qnaDao.insertQnaComment(map) > 0;
	}
}
