<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header class="p-3 text-bg-dark">
    <div class="container-fluid">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
          <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
            <use xlink:href="#bootstrap" />
          </svg>
        </a>

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="${root}/index.jsp" class="nav-link px-2 text-secondary">Home</a></li>
          <li><a href="${root}/notice/list.do" class="nav-link px-2 text-white">공지사항</a></li>
          <li><a href="#" class="nav-link px-2 text-white">오늘의 뉴스</a></li>
          <li><a href="#" class="nav-link px-2 text-white">자유글</a></li>
          <li><a href="#" class="nav-link px-2 text-white">주변탐방</a></li>
          <li><a href="#" class="nav-link px-2 text-white">관심지역 설정</a></li>
          <li><a href="#" class="nav-link px-2 text-white">관심지역 둘러보기</a></li>
        </ul>

        <div class="text-end" id="nav-right">
        </div>
<%@ include file="/WEB-INF/views/include/user_modal.jsp" %>
</header>