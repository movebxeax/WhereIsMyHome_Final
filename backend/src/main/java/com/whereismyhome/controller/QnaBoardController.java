package com.whereismyhome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whereismyhome.model.dto.QnaInfo;
import com.whereismyhome.model.service.QnaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "qna", description = "QnA Board APIs")
@RequestMapping("/api/qna")
@RestController
public class QnaBoardController {
	@Autowired
	private QnaService qnaService;

	@Operation(summary = "Query Posts",
			description = "Query Summary List of QnA Posts",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "500", description = "Fail")
			})
	@GetMapping
	protected ResponseEntity<?> getQnaArticleList(@RequestParam(required = false)String title, @RequestParam(required = false)String author) {
		Map map = new HashMap<String, String>();

		if(title != null && title.length() > 0)
			map.put("title", title);
		else if(author != null && author.length() > 0)
			map.put("author", author);

		List<QnaInfo> res = qnaService.getQnaArticleList(map);
		if(res != null)
			return createResponse(HttpStatus.OK, res);
		else
			return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured while fetching article");
	}

	@Operation(summary = "Write Post",
			description = "Write QnA Post",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "500", description = "Fail")
	})
	@PostMapping
	protected ResponseEntity<?> writeQnaArticle(@RequestBody QnaInfo qnaInfo) {
		qnaInfo.setUuid(UUID.randomUUID().toString());

		boolean res = qnaService.writeQnaArticle(qnaInfo);
		if(res)
			return createResponse(HttpStatus.OK);
		else
			return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured while writing article");
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
				return createResponse(HttpStatus.NOT_FOUND, "Error occured while fetching article detail");
		}
		else
		{
			return createResponse(HttpStatus.NOT_FOUND, "Error occured while fetching article detail");
		}
	}

	@Operation(summary = "Modify Post",
			description = "Update Specific QnA Post",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "404", description = "Article Not Exists"),
					@ApiResponse(responseCode = "500", description = "Article Update Fail")
	})
	@PostMapping("/{articleNo}")
	protected ResponseEntity<?> updateQnaArticle(@PathVariable int articleNo, @RequestBody QnaInfo qnaInfo) {
		QnaInfo checkExist = qnaService.getQnaArticle(articleNo);
		if(checkExist != null)
		{
			qnaInfo.setNo(articleNo);

			boolean res = qnaService.modifyQnaArticle(qnaInfo);
			if(res)
				return createResponse(HttpStatus.OK, qnaInfo);
			else
				return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured while updating article detail");
		}
		else
			return createResponse(HttpStatus.NOT_FOUND, "Error occured while updating article detail - Article doesn't exist");
	}

	@Operation(summary = "Delete Post",
			description = "Delete Specific QnA Post",
			responses = {
					@ApiResponse(responseCode = "200", description = "Success"),
					@ApiResponse(responseCode = "500", description = "Article Delete Fail")
	})
	@DeleteMapping("/{articleNo}")
	protected ResponseEntity<?> deleteQnaArticle(@PathVariable int articleNo) {
		boolean res = qnaService.deleteQnaArticle(articleNo);
		if(res)
			return createResponse(HttpStatus.OK);
		else
			return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured while deleting article");
	}

	protected ResponseEntity<?> createResponse(HttpStatus status, Object response)
	{
		if(status == HttpStatus.OK)
		{
			return ResponseEntity.ok().body(response);
		}
		else
		{
			return ResponseEntity.status(status).body(response);
		}
	}
	
	protected ResponseEntity<?> createResponse(HttpStatus status)
	{
		return createResponse(status, null);
	}
}
