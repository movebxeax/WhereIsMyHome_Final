<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <meta name="description" content="" />
  <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors" />
  <meta name="generator" content="Hugo 0.101.0" />
  <title>Where Is My Home</title>

  <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/product/" />

  <link href="./assets/dist/css/bootstrap.min.css" rel="stylesheet" />

  <style>
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }

    .b-example-divider {
      height: 3rem;
      background-color: rgba(0, 0, 0, 0.1);
      border: solid rgba(0, 0, 0, 0.15);
      border-width: 1px 0;
      box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1), inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
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
  </style>

  <!-- Custom styles for this template -->
  <link href="./css/product.css" rel="stylesheet">
</head>

<body>
  <%@ include file="/WEB-INF/views/include/nav.jsp" %>

  <main>
    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light" style="z-index: 100;">
      <div class="col-md-5 p-lg-5 mx-auto my-5 back">
        <h1 class="display-5 fw-bold" style="color:purple">태희쌤 사랑해요</h1>
        <p class="lead fw-normal">
          우리를 위한 T.H.
        </p>
        <a class="btn btn-outline-secondary" href="${root}/queryData.do">실거래가 조회</a>
      </div>
      <video class="bg-video" playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
        <source src="./img/bg.mp4" type="video/mp4" />
      </video>
    </div>

    <div class="d-md-flex flex-md-equal w-100 my-md-3 ps-md-3">
      <div class="text-bg-dark me-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
        <div class="my-1 py-1" style="height:500px">
          <img src="./img/kimjaehwan.jfif" width="100%" height="480px">
        </div>
      </div>
      <div class="my-3 p-3">
        <div style="font-size: 40px; font-weight: bold;">오늘의 뉴스</div>
        <hr>
        <br>
        <div style="font-size: 35px;">극한 거래 절벽…추석 이후 15억 초과 아..</div>
        <div style="font-size: 20px;">최근 주택시장이 '절벽'을 넘어 '빙하기'로 접어들었다는 평가가 나올 만큼 거래 침체가 심해지자 정부가 추가 규제 완화를 두고 고심에 빠졌다.
          시장에선 정부가 그간 무리한 규제라는 비판이 많았던 15억 원 초과 아파트에 대한 주택담보대출 금지 규제를 우선해 걷어낼 거란 전망이 나온..
        </div>
        <br>
        <div style="font-size: 18px;">전국 7개 지역, 전셋집 경매 넘어가면 내 보증금..</div>
        <div style="font-size: 18px;">벼랑 끝에 선 종부세 특별공제…반쪽 통과시 ..</div>
        <div style="font-size: 18px;">" 침수 지원금, 반씩 나누라니"…집주인-세입자 ..</div>
        <div style="font-size: 18px;">서울시, ‘오세훈표 모아타운‘ 추가 공모에 39..</div>
      </div>
    </div>

  </main>
  

  <%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>

</html>