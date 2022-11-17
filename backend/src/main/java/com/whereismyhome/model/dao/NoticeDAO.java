package com.whereismyhome.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.whereismyhome.model.dto.NoticeInfo;

@Mapper
public interface NoticeDAO {
	
	int insertNotice(NoticeInfo notice);	
	List<NoticeInfo> selectNotices();	
	NoticeInfo selectNotice(int no);	
	int updateNotice(NoticeInfo notice);	
	int removeNotice(int no);
}
