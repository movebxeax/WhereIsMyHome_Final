package com.whereismyhome.model.service;

import java.util.List;

import com.whereismyhome.model.dto.NoticeInfo;

public interface NoticeService {	
	public boolean writeNotice(NoticeInfo notice);
	public List<NoticeInfo> getNotices();
	public NoticeInfo getNotice(int no);
	public boolean modifyNotice(NoticeInfo notice);
	public boolean deleteNotice(int no);
}
