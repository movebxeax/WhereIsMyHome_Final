var marker, infowindow;
var container = document.getElementById("map");
var options = {
  center: new kakao.maps.LatLng(37.5013068, 127.0396597),
  level: 3,
};

var map = new kakao.maps.Map(container, options);

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

var message = '<div style="padding:5px;">SSAFY</div>';

var markers = [];
var infowindows = [];

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(myLatLng, message, imgSrc) {
  // 마커를 생성합니다
  if (imgSrc == -1) {
    marker = new kakao.maps.Marker({
      map: map,
      position: myLatLng,
      //image: markerImage, // 마커이미지 설정
    });
    
    marker.title = message;
  } else {
    var imageSize = new kakao.maps.Size(29, 42), // 마커이미지의 크기입니다
      imageOption = { offset: new kakao.maps.Point(27, 69) };

    var markerImage = new kakao.maps.MarkerImage(
      imgSrc,
      imageSize,
      imageOption
    );

    marker = new kakao.maps.Marker({
      map: map,
      position: myLatLng,
      image: markerImage, // 마커이미지 설정
    });
    
    marker.title = message;
  }

  var iwContent = message, // 인포윈도우에 표시할 내용
    iwRemoveable = true;

  // 인포윈도우를 생성합니다
  infowindow = new kakao.maps.InfoWindow({
    content: iwContent,
    removable: iwRemoveable,
  });

  // 인포윈도우를 마커위에 표시합니다
  infowindow.open(map, marker);

  // 지도 중심좌표를 접속위치로 변경합니다
  map.setCenter(myLatLng);

  // 마커에 마우스오버 이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "mouseover", function () {
    // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, marker);
  });

  // 마커에 마우스아웃 이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "mouseout", function () {
    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
    infowindow.close();
  });

  markers.push(marker);
  infowindows.push(infowindow);
}

function removeMarker() {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
    infowindows[i].close();
  }
}

function removeDesignatedMarker(name)
{
	for (var i = 0; i < markers.length; i++) {
		if(markers[i].title === name)
		{
    		markers[i].setMap(null);
    		infowindows[i].close();
    	}
  }
}
