<template>
  <div>
    <div id="map"></div>
    <trade-side-bar class="side-bar"></trade-side-bar>
  </div>
</template>

<script>
import TradeSideBar from "@/components/trade/TradeSideBar.vue";

export default {
  name: "TradeMap",
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
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
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
