<template>
  <div>
    <div id="map"></div>
    <trade-side-bar class="side-bar" v-bind="apt"></trade-side-bar>
  </div>
</template>

<script>
import TradeSideBar from "@/components/trade/TradeSideBar.vue";
import { mapState, mapActions } from "vuex";
import { aptInfo } from "@/api/trade";
const tradeStore = "tradeStore";

export default {
  name: "TradeMap",
  data() {
    return {
      map: null,
      apt: {},
      markers: [],
      imgHouse: require("@/assets/img/house.png"),
    };
  },
  components: { TradeSideBar },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      //   script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + process.env.VUE_APP_KAKAO_MAP_KEY;
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=980bea8bcd07009e77637df27e1433d7";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(tradeStore, ["apts", "dong"]),
  },
  watch: {
    apts() {
      // 지도 표시
      this.updateMap();
    },
    dong() {
      this.changeCenterMap();
    },
  },
  methods: {
    ...mapActions(tradeStore, ["getAptListWithCds", "getApt"]),

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.501929614341925, 127.03899430413212),
        level: 4,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      let mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      let zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      let base = this;

      kakao.maps.event.addListener(base.map, "dragend", function () {
        var bounds = base.map.getBounds();
        var swLatlng = bounds.getSouthWest();
        var neLatlng = bounds.getNorthEast();

        const params = { minLat: swLatlng.Ma, maxLat: neLatlng.Ma, minLng: swLatlng.La, maxLng: neLatlng.La };
        base.getAptListWithCds(params);
      });
    },
    changeCenterMap() {
      let moveLatLon = new kakao.maps.LatLng(this.dong.lat, this.dong.lng);
      this.map.panTo(moveLatLon);
    },
    updateMap() {
      // 초기화
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];

      var imageSize = new kakao.maps.Size(25, 29);
      // 마커 이미지를 생성합니다

      this.apts.forEach((apt) => {
        var markerImage = new kakao.maps.MarkerImage(this.imgHouse, imageSize);
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: new kakao.maps.LatLng(apt.lat, apt.lng), // 마커를 표시할 위치
          title: apt.aptName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });

        // // 오버레이
        // var content = `<div class="label" style="background:white"><span class="center">${apt.aptName}</span></div>`;

        // // 커스텀 오버레이를 생성합니다
        // let customOverlay = new kakao.maps.CustomOverlay({
        //   position: marker.getPosition(),
        //   content: content,
        //   yAnchor: -0.2,
        //   map: this.map,
        // });

        // kakao.maps.event.addListener(marker, "mouseover", function () {
        //   customOverlay.setMap(this.map);
        // });

        kakao.maps.event.addListener(marker, "click", function () {
          console.log(apt.aptName);
          aptInfo(
            apt.aptCode,
            ({ data }) => {
              console.log(data);
              this.apt = data;
            },
            (error) => {
              console.log(error);
            }
          );
          // this.getApt(this.markers);
        });

        this.markers.push(marker);
      });
    },
  },
};
</script>

<style lang="scss" scoped>
#app {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  .side-bar {
    position: absolute;
    left: 0;
    top: 60px;
    height: 800px;
    bottom: 0;
    z-index: 1000;
  }
}

#map {
  width: 100%;
  height: 800px;
}
</style>
