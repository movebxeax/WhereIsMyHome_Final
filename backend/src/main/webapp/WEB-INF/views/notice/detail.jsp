<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>detail_notice</title>
	<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/product/" />
	<link href="${root}/assets/dist/css/bootstrap.min.css" rel="stylesheet" />
	<!-- Custom styles for this template -->
	<link href="${root}/css/product.css" rel="stylesheet">
	<script src="${root}/assets/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp" %>
		<div class="container">
			<div class="row mt-3">
				<h2 class="text-center">공지사항 내용</h2>
			</div>
			<div class="form-outline">
				<form method="post" action="${root}/notice/modify.do">
					<div class="form-outline mb-6">
						<c:choose>
							<c:when test="${notice.author eq userid }">
								<input type="text" id="subject" name="subject" class="form-control" placeholder="제목"
							value="${notice.subject}" />
							</c:when>
							<c:otherwise>
								<input type="text" id="subject" name="subject" class="form-control" placeholder="제목"
							value="${notice.subject}" readonly/>
							</c:otherwise>
						</c:choose>

						<input type="hidden" name="noticeNo" id="noticeNo"
							value="${notice.no}" readonly>
					</div>
					<div class="form-outline mb-4">
						<c:choose>
							<c:when test="${notice.author eq userid }">
								<textarea class="form-control" id="content" name="content" rows="30"
									placeholder="내용">${notice.content}</textarea>
							</c:when>
							<c:otherwise>
								<textarea class="form-control" id="content" name="content" rows="30"
									placeholder="내용" readonly>${notice.content}</textarea>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="d-flex flex-row-reverse">
						<button type="button" class="btn btn-info me-2 justify-content-end"
							onclick="location.href = '${root}/notice/list.do'">목록</button>
						<c:if test="${notice.author eq userid }">
							<input type="submit" class="btn btn-warning me-2 justify-content-end"
								formaction="${root}/notice/modify.do" value="수정"/>
							<input type="submit" class="btn btn-danger me-2 justify-content-end"
								formaction="${root}/notice/delete.do" value="삭제"/>
						</c:if>
					</div>
				</form>
			</div>
		</div>
		<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>