package com.whereismyhome.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		return createResponse(noticeService.getNotice(noticeNo));
	}

	@PutMapping("/{noticeNo}")
	protected ResponseEntity<?> modifyNoticeDetail(@RequestBody NoticeInfo noticeInfo, HttpSession session) {

		// !!! todo !!!
		// author check required

		return createResponse(noticeService.modifyNotice(noticeInfo));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	protected ResponseEntity<?> registerNotice(@RequestBody NoticeInfo noticeInfo) {		
		return createResponse(noticeService.writeNotice(noticeInfo));
	}

	@DeleteMapping("/{noticeNo}")
	protected ResponseEntity<?> deleteNoticeDetail(@PathVariable int noticeNo) {

		// !!! todo !!!
		// author check required

		return createResponse(noticeService.deleteNotice(noticeNo));
	}
}
