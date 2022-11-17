<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list_notice</title>
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
			<h2 class="text-center">공지사항</h2>
		</div>
		<div class="row">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty noticeList}">
							<tr>
								<td colspan="3">등록된 공지사항이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${requestScope.noticeList}" var="notice"
								varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td><a
										href="${root}/notice/detail.do?noticeNo=${notice.no}">${notice.subject}</a></td>
									<td>${notice.author}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<c:if test="${not empty userid}">
			<button type="button" class="btn btn-info me-2" onclick = "location.href = '${root}/notice/write.jsp'">글쓰기</button>
			</c:if>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>

</html>