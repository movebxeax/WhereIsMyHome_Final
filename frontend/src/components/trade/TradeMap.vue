<template>
  <div class="ma-10" rounded-xl>
    <v-card outlined>
      <v-row no-gutters>
        <v-col cols="auto">
          <trade-side-bar class="sideBar" @mapRelayout="mapRelayout"></trade-side-bar>
        </v-col>
        <v-col>
          <div id="map"></div>
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
import TradeSideBar from "@/components/trade/TradeSideBar.vue";
import { mapActions, mapGetters } from "vuex";
import { dongMarkerInfo, gugunMarkerInfo } from "@/api/trade";
const tradeStore = "tradeStore";

const DEALYEAR_DONG_LIMIT = 5;
const DEALYEAR_GUGUN_LIMIT = 7;

export default {
  name: "TradeMap",
  data() {
    return {
      map: null,
      markers: [],
      clusterer: null,
      imgHouse: require("@/assets/img/house.png"),
    };
  },
  components: { TradeSideBar },

  computed: {
    ...mapGetters(tradeStore, [
      "apts",
      "dong",
      "apt",
      "searchOptions",
      "filterOptions",
      "aptsByFilter",
      "inputDongcode",
    ]),
  },
  watch: {
    apts: {
      deep: true,
      handler() {
        if (this.apts.length >= 0) {
          this.updateMap();
        }
      },
    },
    dong() {
      if (this.dong) {
        console.log(this.dong);
      }
      this.changeCenterMap();
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      //   script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + process.env.VUE_APP_KAKAO_MAP_KEY +"&libraries=services,clusterer";
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=980bea8bcd07009e77637df27e1433d7&libraries=services,clusterer";
      document.body.appendChild(script);
    }
  },
  created() {
    this.clearApt();
    if (this.inputDongcode) {
      this.setDong(this.searchOptions.find((dong) => dong.dongcode === this.inputDongcode));
    }
  },
  methods: {
    ...mapActions(tradeStore, [
      "getAptListWithCds",
      "getApt",
      "getAptList",
      "clearAptList",
      "setDong",
      "setApt",
      "clearApt",
    ]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.501929614341925, 127.03899430413212),
        level: 3,
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

      // 드래그 이벤트
      kakao.maps.event.addListener(this.map, "dragend", () => {
        let level = this.map.getLevel();

        if (level >= DEALYEAR_GUGUN_LIMIT) {
          this.getGugunMarkers();
        } else if (level >= DEALYEAR_DONG_LIMIT) {
          this.getDongMarkers();
        } else {
          var bounds = this.map.getBounds();
          var swLatlng = bounds.getSouthWest();
          var neLatlng = bounds.getNorthEast();

          const params = { minLat: swLatlng.Ma, maxLat: neLatlng.Ma, minLng: swLatlng.La, maxLng: neLatlng.La };
          this.clearMarkers();
          this.getAptListWithCds(params);
        }
      });

      // 확대, 축소 이벤트
      kakao.maps.event.addListener(this.map, "idle", () => {
        let level = this.map.getLevel();

        if (level >= DEALYEAR_GUGUN_LIMIT) {
          this.getGugunMarkers();
        } else if (level >= DEALYEAR_DONG_LIMIT) {
          this.getDongMarkers();
        } else {
          var bounds = this.map.getBounds();
          var swLatlng = bounds.getSouthWest();
          var neLatlng = bounds.getNorthEast();

          const params = { minLat: swLatlng.Ma, maxLat: neLatlng.Ma, minLng: swLatlng.La, maxLng: neLatlng.La };
          this.clearMarkers();
          this.getAptListWithCds(params);
        }
      });

      var bounds = this.map.getBounds();
      var swLatlng = bounds.getSouthWest();
      var neLatlng = bounds.getNorthEast();

      const params = { minLat: swLatlng.Ma, maxLat: neLatlng.Ma, minLng: swLatlng.La, maxLng: neLatlng.La };
      this.clearMarkers();
      this.getAptListWithCds(params);
    },
    changeCenterMap() {
      console.log("change center");
      let moveLatLon = new kakao.maps.LatLng(this.dong.lat, this.dong.lng);
      this.map.panTo(moveLatLon);
    },
    updateMap() {
      // 레벨이 6이상이면 아파트 마커 사용 X
      let level = this.map.getLevel();

      // if (level >= DEALYEAR_DONG_LIMIT) {
      //   if (this.apts.length > 0) {
      //     this.clearAptList();
      //   }
      //   this.getDongMarkers();
      //   return;
      // }

      if (level >= DEALYEAR_GUGUN_LIMIT) {
        if (this.apts.length > 0) {
          this.clearAptList();
        }
        this.getGugunMarkers();
        return;
      } else if (level >= DEALYEAR_DONG_LIMIT) {
        if (this.apts.length > 0) {
          this.clearAptList();
        }
        this.getDongMarkers();
        return;
      }
      // 초기화
      this.clearMarkers();

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

        // 클릭 이벤트
        kakao.maps.event.addListener(marker, "click", () => {
          // this.getApt(apt.aptCode);
          this.setApt(apt);
        });

        this.markers.push(marker);
      });
    },
    getDongMarkers() {
      // 마커 데이터 갱신
      this.clearMarkers();
      let bounds = this.map.getBounds();
      let swLatlng = bounds.getSouthWest();
      let neLatlng = bounds.getNorthEast();

      const params = { minLat: swLatlng.Ma, maxLat: neLatlng.Ma, minLng: swLatlng.La, maxLng: neLatlng.La };
      dongMarkerInfo(
        params,
        ({ data }) => {
          // 커스텀 오버레이 생성
          data.forEach((dong) => {
            let content =
              `<div class="customoverlay" id="${dong.dongCode}" style="background:white; cursor:pointer;">` +
              `<div class="customoverlay-dong">` +
              `        <span class="customoverlay-dong-count">${dong.aptCount}</span>` +
              `        <span class="customoverlay-dong-name">${dong.dongName}</span>` +
              `</div>` +
              "</div>";

            // 커스텀 오버레이가 표시될 위치입니다
            let position = new kakao.maps.LatLng(dong.lat, dong.lng);

            // 커스텀 오버레이를 생성합니다
            let customOverlay = new kakao.maps.CustomOverlay({
              map: this.map,
              position: position,
              content: content,
              yAnchor: 1,
            });

            // 커스텀 오버레이 클릭 이벤트 설정

            const close = document.getElementById(dong.dongCode);
            close.onclick = () => {
              let moveLatLon = new kakao.maps.LatLng(dong.lat, dong.lng);
              this.map.panTo(moveLatLon);
              this.map.setLevel(3);
            };

            this.markers.push(customOverlay);
          });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugunMarkers() {
      // 마커 데이터 갱신
      this.clearMarkers();
      let bounds = this.map.getBounds();
      let swLatlng = bounds.getSouthWest();
      let neLatlng = bounds.getNorthEast();

      const params = { minLat: swLatlng.Ma, maxLat: neLatlng.Ma, minLng: swLatlng.La, maxLng: neLatlng.La };
      gugunMarkerInfo(
        params,
        ({ data }) => {
          // 커스텀 오버레이 생성
          data.forEach((gugun) => {
            let content =
              `<div class="customoverlay" id="${gugun.gugunCode}" style="background:white; cursor:pointer">` +
              `<div class="customoverlay-gugun">` +
              `    <div class="customoverlay-gugun-name">${gugun.gugunName}</div>` +
              `</div>` +
              "</div>";

            // 커스텀 오버레이가 표시될 위치입니다
            let position = new kakao.maps.LatLng(gugun.lat, gugun.lng);

            // 커스텀 오버레이를 생성합니다
            let customOverlay = new kakao.maps.CustomOverlay({
              map: this.map,
              position: position,
              content: content,
              yAnchor: 1,
            });

            // 커스텀 오버레이 클릭 이벤트 설정
            const close = document.getElementById(gugun.gugunCode);
            close.onclick = () => {
              let moveLatLon = new kakao.maps.LatLng(gugun.lat, gugun.lng);
              this.map.panTo(moveLatLon);
              this.map.setLevel(DEALYEAR_DONG_LIMIT);
            };

            this.markers.push(customOverlay);
          });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    clearMarkers() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
    },
    mapRelayout() {
      setTimeout(() => {
        this.map.relayout();
      }, 100);
    },
  },
};
</script>

<style lang="scss" scoped>
.sideBar {
  width: 100%;
  height: 800px;
}

#map {
  width: 100%;
  height: 800px;
}
</style>
