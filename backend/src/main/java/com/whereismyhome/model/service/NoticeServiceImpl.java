package com.whereismyhome.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whereismyhome.model.dao.NoticeDAO;
import com.whereismyhome.model.dto.NoticeInfo;

@Service
public class NoticeServiceImpl implements NoticeService {
	NoticeDAO noticeDao;
	
	@Autowired
	public NoticeServiceImpl(NoticeDAO noticeDao) {
		this.noticeDao = noticeDao;
	}

	public boolean writeNotice(NoticeInfo notice) {
		// 공지사항 등록
		return noticeDao.insertNotice(notice) > 0;
	}
	
	public List<NoticeInfo> getNotices(){
		// 조회
		return noticeDao.selectNotices();
	}
	public NoticeInfo getNotice(int no){
		// 조회 1개
		return noticeDao.selectNotice(no);
	}
	
	public boolean modifyNotice(NoticeInfo notice){
		// 수정
		return noticeDao.updateNotice(notice) > 0;
	}
	
	public boolean deleteNotice(int no){
		// 삭제
		return noticeDao.removeNotice(no) > 0;
	}

}
