<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/product/" />

  <link href="${root}/assets/dist/css/bootstrap.min.css" rel="stylesheet" />

</head>
<body>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<c:if test="${not empty msg}">
<h3>${msg}</h3>
<a href="${root}/index.do">메인화면</a>
</c:if>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>