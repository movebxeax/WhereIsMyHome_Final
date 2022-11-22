package com.whereismyhome.model.service.notice;

import java.util.List;

import com.whereismyhome.model.dto.notice.NoticeInfo;

public interface NoticeService {	
	public boolean writeNotice(NoticeInfo notice);
	public List<NoticeInfo> getNotices();
	public NoticeInfo getNotice(int no);
	public boolean modifyNotice(NoticeInfo notice);
	public boolean deleteNotice(int no);
	public boolean updateNoticeArticleViewCount(int noticeNo);
}
