package com.whereismyhome.controller;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.whereismyhome.model.dto.notice.NoticeInfo;
import com.whereismyhome.model.service.notice.NoticeService;
import com.whereismyhome.util.ResponseManager;

import lombok.extern.slf4j.Slf4j;

@Transactional
@RequestMapping("/api/notice")
@SessionAttributes("userid")
@RestController
public class NoticeController extends ResponseManager {

	@Autowired
	private NoticeService noticeService;

	@GetMapping
	protected ResponseEntity<?> getNoticeList() {
		return createResponse(noticeService.getNotices());
	}

	@GetMapping("/{noticeNo}")
	protected ResponseEntity<?> getNoticeDetail(@PathVariable int noticeNo) {
		noticeService.updateNoticeArticleViewCount(noticeNo);
		
		return createResponse(noticeService.getNotice(noticeNo));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	protected ResponseEntity<?> registerNotice(@RequestBody NoticeInfo noticeInfo, Principal principal) {
		noticeInfo.setAuthor(principal.getName());
		
		boolean res = noticeService.writeNotice(noticeInfo);
		
		if(res)
			return createResponse(noticeService.writeNotice(noticeInfo));
		else
			return createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/{noticeNo}")
	protected ResponseEntity<?> modifyNoticeDetail(@RequestBody NoticeInfo noticeInfo) {
		boolean res = noticeService.modifyNotice(noticeInfo);
		
		if(res)
			return createResponse(noticeInfo);
		else
			return createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{noticeNo}")
	protected ResponseEntity<?> deleteNoticeDetail(@PathVariable int noticeNo) {
		boolean res = noticeService.deleteNotice(noticeNo);
		
		if(res)
			return createResponse(res);
		else
			return createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
