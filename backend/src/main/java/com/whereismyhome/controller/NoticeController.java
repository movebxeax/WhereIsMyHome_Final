package com.whereismyhome.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.whereismyhome.config.exception.InternalServerErrorException;
import com.whereismyhome.config.exception.NotFoundErrorException;
import com.whereismyhome.model.dto.notice.NoticeInfo;
import com.whereismyhome.model.service.notice.NoticeService;
import com.whereismyhome.util.ResponseManager;

@Transactional
@RequestMapping("/api/notice")
@SessionAttributes("userid")
@RestController
public class NoticeController extends ResponseManager {

	@Autowired
	private NoticeService noticeService;

	@GetMapping
	protected ResponseEntity<?> getNoticeList() {
		try {
			return createResponse(noticeService.getNotices());
		} catch(Exception e) {
			throw new NotFoundErrorException();
		}
	}

	@GetMapping("/{noticeNo}")
	protected ResponseEntity<?> getNoticeDetail(@PathVariable int noticeNo) throws Exception {
		try {
			noticeService.updateNoticeArticleViewCount(noticeNo);
			
			if(noticeService != null)
				return createResponse(noticeService.getNotice(noticeNo));
			else
				throw new NotFoundErrorException();
		} catch(Exception e) {
			throw new InternalServerErrorException("nooooooooooo");
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	protected ResponseEntity<?> registerNotice(@RequestBody NoticeInfo noticeInfo, Principal principal) throws Exception {
		noticeInfo.setAuthor(principal.getName());

		boolean res = noticeService.writeNotice(noticeInfo);

		if(res)
			return createResponse("");
		else
			throw new InternalServerErrorException("공지사항 등록 실패");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/{noticeNo}")
	protected ResponseEntity<?> modifyNoticeDetail(@PathVariable int noticeNo, @RequestBody NoticeInfo noticeInfo) throws Exception {
		NoticeInfo checkExist = noticeService.getNotice(noticeNo);
		if(checkExist != null)
		{
			boolean res = noticeService.modifyNotice(noticeInfo);

			if(res)
				return createResponse(noticeInfo);
			else
				throw new InternalServerErrorException("공지사항 수정 실패");
		}
		else
			throw new NotFoundErrorException("공지사항 수정 실패 - 대상 게시물 없음");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{noticeNo}")
	protected ResponseEntity<?> deleteNoticeDetail(@PathVariable int noticeNo) throws Exception {
		NoticeInfo checkExist = noticeService.getNotice(noticeNo);
		if(checkExist != null)
		{
			boolean res = noticeService.deleteNotice(noticeNo);

			if(res)
				return createResponse(res);
			else
				throw new InternalServerErrorException("공지사항 삭제 실패");
		}
		else
			throw new NotFoundErrorException("공지사항 삭제 실패 - 대상 게시물 없음");
	}
}
