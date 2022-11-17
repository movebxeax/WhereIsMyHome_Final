<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write_notice</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/product/" />

<link href="${root}/assets/dist/css/bootstrap.min.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="${root}/css/product.css" rel="stylesheet">
<script src="${root}/assets/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
	<div class="container">
		<div class="row mt-3">
			<h2 class="text-center">공지사항 등록</h2>
		</div>
		<form method="post" action="${root}/notice/write.do">
  				<div class="form-outline mb-6">
    				<input type="text" id="subject" name="subject" class="form-control" placeholder="제목"/>
    				<input type="hidden" id="author" name="author" value="${userid}"/>
  				</div>
 				<div class="form-outline mb-4">
    				<textarea class="form-control" id="content" name="content" rows="30" placeholder="내용"></textarea>
 				</div>
 				<c:choose>
 				<c:when test="not empty ${userid}">
 				<div><span>test1</span></div>
 				</c:when>
 				</c:choose>
 				<div class="align-right">
 					<input type="submit" class="btn btn-info me-2" value = "글쓰기">
 				</div>
			</form>
	</div>
	
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>