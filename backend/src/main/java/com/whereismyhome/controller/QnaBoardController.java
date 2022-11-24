package com.whereismyhome.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.config.exception.InternalServerErrorException;
import com.whereismyhome.config.exception.NotFoundErrorException;
import com.whereismyhome.model.dto.qna.QnaInfo;
import com.whereismyhome.model.service.qna.QnaService;
import com.whereismyhome.util.ResponseManager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Transactional
@Tag(name = "qna", description = "QnA Board APIs")
@RequestMapping("/api/qna")
@RestController
public class QnaBoardController extends ResponseManager {
	@Autowired
	private QnaService qnaService;

	@Operation(summary = "Query Posts",
			description = "Query Summary List of QnA Posts",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "500", description = "Fail")
			})
	@GetMapping
	protected ResponseEntity<?> getQnaArticleList(@RequestParam(required = false)String title,
			@RequestParam(required = false)String author,
			@RequestParam(required = false)Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();

		if(title != null && title.length() > 0)
			map.put("title", title);
		else if(author != null && author.length() > 0)
			map.put("author", author);
		
		if(limit != null)
			map.put("limit", limit);

		List<QnaInfo> res = qnaService.getQnaArticleList(map);
		if(res != null)
			return createResponse(HttpStatus.OK, res);
		else
			throw new InternalServerErrorException("QnA 목록 불러오기 실패");
	}

	@Operation(summary = "Write Post",
			description = "Write QnA Post",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "500", description = "Fail")
	})
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
	@PostMapping
	protected ResponseEntity<?> writeQnaArticle(@RequestBody QnaInfo qnaInfo) {
		qnaInfo.setUuid(UUID.randomUUID().toString());

		boolean res = qnaService.writeQnaArticle(qnaInfo);
		if(res)
			return createResponse(HttpStatus.OK);
		else
			throw new InternalServerErrorException("QnA 작성 실패");
	}

	@Operation(summary = "Query Post",
			description = "Query Specific QnA Post",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "404", description = "Fail")
	})
	@GetMapping("/{articleNo}")
	protected ResponseEntity<?> getQnaArticle(@PathVariable int articleNo) {
		boolean countUpdateRes = qnaService.updateQnaArticleViewCount(articleNo);
		if(countUpdateRes)
		{
			QnaInfo qnaInfo = qnaService.getQnaArticle(articleNo);
			if(qnaInfo != null)
				return createResponse(HttpStatus.OK, qnaInfo);
			else
				throw new NotFoundErrorException("QnA 게시글 불러오기 실패");
		}
		else
		{
			throw new InternalServerErrorException("QnA 게시글 불러오기 실패");
		}
	}

	@Operation(summary = "Modify Post",
			description = "Update Specific QnA Post",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "404", description = "Article Not Exists"),
					@ApiResponse(responseCode = "500", description = "Article Update Fail")
	})
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
	@PostMapping("/{articleNo}")
	protected ResponseEntity<?> updateQnaArticle(@PathVariable int articleNo, @RequestBody QnaInfo qnaInfo, Principal principal) {
		QnaInfo checkExist = qnaService.getQnaArticle(articleNo);
		if(checkExist != null)
		{
			if(!checkExist.getAuthor().equals(principal.getName()))
				return createResponse(HttpStatus.UNAUTHORIZED);
			
			qnaInfo.setNo(articleNo);

			boolean res = qnaService.modifyQnaArticle(qnaInfo);
			if(res)
				return createResponse(HttpStatus.OK, qnaInfo);
			else
				throw new InternalServerErrorException("QnA 게시글 수정 실패");
		}
		else
			throw new NotFoundErrorException("QnA 게시글 수정 실패 - 수정할 게시글 없음");
	}

	@Operation(summary = "Delete Post",
			description = "Delete Specific QnA Post",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "500", description = "Article Delete Fail")
	})
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
	@DeleteMapping("/{articleNo}")
	protected ResponseEntity<?> deleteQnaArticle(@PathVariable int articleNo, Principal principal) {
		QnaInfo checkExist = qnaService.getQnaArticle(articleNo);

		if(checkExist != null)
		{
			if(!checkExist.getAuthor().equals(principal.getName()))
				return createResponse(HttpStatus.UNAUTHORIZED);

			boolean res = qnaService.deleteQnaArticle(articleNo);
			if(res)
				return createResponse(HttpStatus.OK);
			else
				throw new InternalServerErrorException("QnA 게시글 삭제 실패");
		}
		else
			throw new NotFoundErrorException("QnA 게시글 삭제 실패 - 삭제할 게시글 없음");
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
	@PostMapping("/comment/{articleNo}")
	protected ResponseEntity<?> writeComment(@PathVariable int articleNo, @RequestBody Map<String, Object> map, Principal principal) {
		QnaInfo checkExist = qnaService.getQnaArticle(articleNo);
		if(checkExist != null)
		{
			map.put("articleNo", articleNo);
			map.put("author", principal.getName());
			
			boolean res = qnaService.writeQnaComment(map);
			
			if(res)
				return createResponse(HttpStatus.OK);
			else
				throw new InternalServerErrorException("QnA 댓글 작성 실패");
		}
		else
			throw new NotFoundErrorException("QnA 댓글 작성 실패 - 대상 게시글 없음");
	}
}
