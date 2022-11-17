<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
<meta name="generator" content="Hugo 0.101.0" />
<title>Where Is My Home</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/product/" />

<link href="./assets/dist/css/bootstrap.min.css" rel="stylesheet" />

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, 0.1);
	border: solid rgba(0, 0, 0, 0.15);
	border-width: 1px 0;
	box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1), inset 0 0.125em
		0.5em rgba(0, 0, 0, 0.15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -0.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}

.container {
	max-width: 90%;
}

video.bg-video {
	width: 100%;
	background-size: cover;
	transition: 1s opacity;
	opacity: 0.5;
	position: absolute;
	z-index: -1500;
	top: 0;
	left: 0;
}

.item2 {
	width: 60%;
	height: 400px;
}
*
/
</style>

<!-- Custom styles for this template -->
<link href="./css/product.css" rel="stylesheet">
</head>

<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>

	<main>
		<!-- 수정-->
		<div class="container-fluid">
			<h2 class="text-center mt-5 mb-3">아파트 매매 정보</h2>
			<form>
				<div class="d-flex justify-content-center">
					<div class="row col-md-12 justify-content-center mb-2">
						<div class="form-group col-md-2">
							<select class="form-select bg-secondary text-light" id="sido">
								<option value="">시도선택</option>
							</select>
						</div>
						<div class="form-group col-md-1">
							<select class="form-select bg-secondary text-light" id="gugun">
								<option value="">구군선택</option>
							</select>
						</div>
						<div class="form-group col-md-1">
							<select class="form-select bg-secondary text-light" id="dong">
								<option value="">동선택</option>
							</select>
						</div>
						<div class="form-group col-md-1">
							<select class="form-select bg-dark text-light" id="year"></select>
						</div>
						<div class="form-group col-md-1">
							<select class="form-select bg-dark text-light" id="month">
								<option value="">매매월선택</option>
							</select>
						</div>
						<div class="form-group col-md-2">
							<input type="text" class="form-control" name="aptName" placeholder="검색 키워드" id="name" onkeydown="return event.key != 'Enter';"></input>
						</div>
						<div class="form-group col-md-1 form-check form-switch container">							
						<div>
						<c:choose>
						<c:when test="${not empty userid}">
							<input class="form-check-input align-middle" type="checkbox" id="interest" value="check" style="wdith:60px;height:18px;">
						</c:when>
						<c:otherwise>
						<input class="form-check-input align-middle" type="checkbox" id="interest" value="check" style="wdith:60px;height:18px;" disabled>
						</c:otherwise>
						</c:choose>
							<label class="form-check-label align-middle" for="interest" style="font-size:16px">관심지역설정</label>
						</div>
						<div>
						<input class="form-check-input align-middle" type="checkbox" id="innersearch" value="innersearch" style="wdith:60px;height:18px;">
						<label class="form-check-label align-middle" for="innersearch" style="font-size:16px">결과재검색</label>
						</div>
						</div>
						<div class="form-group col-md-1 form-check form-switch container">
						<div>
						<input class="form-check-input align-middle" type="checkbox" id="commercial" value="comme" style="wdith:60px;height:18px;">
						<label class="form-check-label align-middle" for="commercial" style="font-size:16px">업종정보</label>
						<select class="form-select bg-secondary text-light" id="distance">
						<option value="500">500</option>
						<option value="1000">1000</option>
						<option value="1500">1500</option>
						<option value="2000">2000</option>
						<option value="2000">2500</option>
						<option value="2000">3000</option>
						</select>
						</div>
						</div>
						<div class="form-group col-md-1">
							<button type="button" id="list-btn"
								class="btn btn-outline-primary"
								onclick="javascript:findDealData()">매매정보 검색</button>
						</div>
					</div>
				</div>
			</form>
			<div class="d-flex flex-row" style="height: 50vh">
				<div class="p-2 w-25 table-wrapper-scroll-y" style="overflow-y:auto;" id="dealData">
					<table class="table table-striped table-responsive" id="dealTable">
						<tbody id="dealTableBody">
						</tbody>
					</table>
				</div>
				<div id="map" class="w-75 justify-content-end"></div>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7c8a89b96bbbcc8a4d58160e4548c95d"></script>
			</div>
		</div>
	</main>

	<script src="${root}/assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="${root}/js/optionInit.js"></script>
	<script src="${root}/js/deal.js"></script>
	<script src="${root}/js/kakaomap.js"></script>
	
	<c:if test="${not empty userid}">
	<script>
	window.addEventListener("load", function () {
		setInterestInfoIfExist();		  
		});
	</script>
	</c:if>
	
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>

</html>