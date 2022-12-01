package com.whereismyhome.model.dao.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.notice.NoticeInfo;

@Mapper
public interface NoticeDAO {
	
	int insertNotice(NoticeInfo notice);	
	List<NoticeInfo> selectNotices();	
	NoticeInfo selectNotice(int no);	
	int updateNotice(NoticeInfo notice);	
	int removeNotice(int no);
	int increaseViewCount(int articleNo);
}
