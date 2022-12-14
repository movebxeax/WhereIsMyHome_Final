<template>
  <div class="ma-10" rounded-xl>
    <v-card outlined>
      <v-row no-gutters>
        <v-col cols="auto">
          <trade-side-bar
            class="sideBar"
            @mapRelayout="mapRelayout"
            @changeCenterBySearch="changeCenterMap"
            @changeCategories="onChangeCategories"
          ></trade-side-bar>
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

const LEVEL_DONG = 5;
const LEVEL_GUGUN = 7;
const INITIAL_DONGCODE = 1171010500;
const INITIAL_LAT = 37.503517;
const INITIAL_LNG = 127.1035697;
const CATEGORYCODES = ["BK9", "MT1", "PM9", "OL7", "CE7", "CS2"];
export default {
  name: "TradeMap",
  data() {
    return {
      map: null,
      markers: [],
      imgHouse: require("@/assets/img/house.png"),
      ps: null,
      categories: [false, false, false, false, false, false],
      rcvdCategories: [],
      categoryMarkers: [[], [], [], [], [], []],
      curCategoryIdx: -1,
      curCategory: "",
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
      handler() {
        if (this.apts.length > 0) {
          this.updateMap();
        }
      },
    },
    // markers: {
    //   deep: true,
    //   handler() {
    //     if (this.markers.length >= 0) {
    //       this.updateMap();
    //     }
    //   }
    // }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      //   script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + process.env.VUE_APP_KAKAO_MAP_KEY +"&libraries=services,clusterer";
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=980bea8bcd07009e77637df27e1433d7&libraries=services,clusterer";
      /* global kakao */
      script.addEventListener("load", () => {
        console.log("loaded", window.kakao);
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
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
      "setDong",
      "setApt",
      "clearApt",
      "clearApts",
    ]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(INITIAL_LAT, INITIAL_LNG),
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

      // 장소 검색 객체 생성
      this.ps = new kakao.maps.services.Places(this.map);

      // 드래그 이벤트 등록
      // this.addKakaoEvent("dragend");

      // 확대, 축소 이벤트 등록
      this.addKakaoEvent("idle");

      // 동이 있으면 해당 동 조회 없으면 초기 지역 조회
      if (this.dong && this.dong.dongcode != INITIAL_DONGCODE) {
        this.changeCenterMap();
      } else {
        let moveLatLon = new kakao.maps.LatLng(INITIAL_LAT, INITIAL_LNG + 0.0001);
        this.map.panTo(moveLatLon);
      }
    },

    updateMap() {
      let level = this.map.getLevel();

      this.clearMarkers();

      if (level >= LEVEL_GUGUN) {
        if (this.apts.length > 0) {
          this.clearApts();
        }
        this.getMarkers(gugunMarkerInfo, false);
        return;
      } else if (level >= LEVEL_DONG) {
        if (this.apts.length > 0) {
          this.clearApts();
        }
        this.getMarkers(dongMarkerInfo, true);
        return;
      } else {
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
            this.setApt(apt);
          });

          this.markers.push(marker);
        });
      }
    },

    changeCenterMap() {
      if (window.kakao && window.kakao.maps) {
        if (this.map.getLevel() >= LEVEL_DONG) {
          this.map.setLevel(4);
        }
        let moveLatLon = new kakao.maps.LatLng(this.dong.lat, this.dong.lng);
        this.map.panTo(moveLatLon);
      }
    },

    mapRelayout() {
      setTimeout(() => {
        this.map.relayout();
      }, 100);
    },

    addKakaoEvent(type) {
      kakao.maps.event.addListener(this.map, type, this.addKakaoEventHandler());
    },
    addKakaoEventHandler() {
      return () => {
        let level = this.map.getLevel();

        if (level >= LEVEL_GUGUN) {
          // this.getMarkers(gugunMarkerInfo, false);
          this.updateMap();
        } else if (level >= LEVEL_DONG) {
          // this.getMarkers(dongMarkerInfo, true);
          this.updateMap();
        } else {
          var bounds = this.map.getBounds();
          var swLatlng = bounds.getSouthWest();
          var neLatlng = bounds.getNorthEast();

          const params = { minLat: swLatlng.Ma, maxLat: neLatlng.Ma, minLng: swLatlng.La, maxLng: neLatlng.La };
          this.getAptListWithCds(params);
        }
      };
    },

    getMarkers(getMarkerInfo, isDong) {
      // 마커 데이터 갱신
      let bounds = this.map.getBounds();
      let swLatlng = bounds.getSouthWest();
      let neLatlng = bounds.getNorthEast();

      const params = { minLat: swLatlng.Ma, maxLat: neLatlng.Ma, minLng: swLatlng.La, maxLng: neLatlng.La };
      getMarkerInfo(
        params,
        ({ data }) => {
          // 커스텀 오버레이 생성
          data.forEach((detail) => {
            let content = "";
            if (isDong) {
              content = this.getDongOverlay(detail);
            } else {
              content = this.getGugunOverlay(detail);
            }

            // 커스텀 오버레이가 표시될 위치입니다
            let position = new kakao.maps.LatLng(detail.lat, detail.lng);

            // 커스텀 오버레이를 생성합니다
            let customOverlay = new kakao.maps.CustomOverlay({
              map: this.map,
              position: position,
              content: content,
              yAnchor: 1,
            });

            //커스텀 오버레이 클릭 이벤트 설정
            let close = null;
            if (isDong) {
              close = document.getElementById(detail.dongCode);
            } else {
              close = document.getElementById(detail.gugunCode);
            }
            close.onclick = () => {
              if (isDong) {
                this.map.setLevel(LEVEL_DONG - 1);
              } else {
                this.map.setLevel(LEVEL_GUGUN - 1);
              }
              let moveLatLon = new kakao.maps.LatLng(detail.lat, detail.lng);
              this.map.panTo(moveLatLon);
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

    getDongOverlay(dong) {
      const content =
        `<div class="customoverlay" id="${dong.dongCode}" style="background:white; cursor:pointer;">` +
        `<div class="customoverlay-dong">` +
        `        <span class="customoverlay-dong-count">${dong.aptCount}</span>` +
        `        <span class="customoverlay-dong-name">${dong.dongName}</span>` +
        `</div>` +
        "</div>";
      return content;
    },
    getGugunOverlay(gugun) {
      const content =
        `<div class="customoverlay" id="${gugun.gugunCode}" style="background:white; cursor:pointer">` +
        `<div class="customoverlay-gugun">` +
        `    <div class="customoverlay-gugun-name">${gugun.gugunName}</div>` +
        `</div>` +
        "</div>";
      return content;
    },

    onChangeCategories(selectedCategories) {
      this.rcvdCategories = selectedCategories;

      // TODO
      for (let i = 0; i < 6; i++) {
        if (this.rcvdCategories.includes(i)) {
          if (!this.categories[i]) {
            console.log(i, "미선택 -> 선택");
            this.onClickCategory(i, true);
          } else {
            console.log(i, "선택 -> 선택");
          }
        } else {
          if (this.categories[i]) {
            console.log(i, "선택 -> 미선택");
            this.onClickCategory(i, false);
          } else {
            console.log(i, "미선택 -> 미선택");
          }
        }
      }
    },
    // 카테고리를 클릭했을 때 호출되는 함수입니다
    onClickCategory(index, isOn) {
      // let currCategory = CATEGORYCODES[index];
      this.curCategory = CATEGORYCODES[index];
      this.curCategoryIdx = index;
      if (isOn) {
        // 미선택 -> 선택
        kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
        this.categories[index] = true;
        this.searchPlaces();
      } else {
        // 선택 -> 미선택
        kakao.maps.event.removeListener(this.map, "idle", this.searchPlaces);
        this.categories[index] = false;
        this.removeMarker();
      }
    },
    searchPlaces() {
      // 없으면 리턴

      console.log(this.curCategory);

      // 기존 오버레이 제거
      this.removeMarker();
      // 카테고리 코드, 콜백, 옵션
      this.ps.categorySearch(this.curCategory, this.placesSearchCB, { useMapBounds: true });
    },
    removeMarker() {
      if (this.categoryMarkers[this.curCategoryIdx]) {
        for (var i = 0; i < this.categoryMarkers[this.curCategoryIdx].length; i++) {
          this.categoryMarkers[this.curCategoryIdx][i].setMap(null);
        }
        this.categoryMarkers[this.curCategoryIdx] = [];
      }
    },
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    displayPlaces(places) {
      let order = this.curCategoryIdx;
      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);
        console.log(marker);
        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        // (function (marker, place) {
        //   kakao.maps.event.addListener(marker, "click", function () {
        //     this.displayPlaceInfo(place);
        //   });
        // })(marker, places[i]);
      }
      // 마커 표시
    },
    addMarker(position, order) {
      let content = null;
      if (order == 0) {
        content = `<div class="mdi mdi-bank categoryIcon"></div>`;
      } else if (order == 1) {
        content = `<div class="mdi mdi-cart categoryIcon"></div>`;
      } else if (order == 2) {
        content = `<div class="mdi mdi-pill categoryIcon"></div>`;
      } else if (order == 3) {
        content = `<div class="mdi mdi-barrel categoryIcon"></div>`;
      } else if (order == 4) {
        content = `<div class="mdi mdi-coffee categoryIcon"></div>`;
      } else if (order == 5) {
        content = `<div class="mdi mdi-store-24-hour categoryIcon"></div>`;
      }
      // (markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions)),
      //   (marker = new kakao.maps.Marker({
      //     position: position, // 마커의 위치
      //     image: markerImage,
      //   }));

      // marker.setMap(map); // 지도 위에 마커를 표출합니다
      // markers.push(marker); // 배열에 생성된 마커를 추가합니다

      var customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
      });

      // 커스텀 오버레이를 지도에 표시합니다
      customOverlay.setMap(this.map);
      this.categoryMarkers[order].push(customOverlay);
      console.log(content);
      return customOverlay;
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
