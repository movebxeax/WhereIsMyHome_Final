<template>
  <div>
    <v-container>
      <v-row dense>
        <v-col>
          <v-card color="primary" dark>
            <v-card-title>
              <div class="interest-btn-container">
                <div v-if="isLogin">
                  <v-btn
                    :ripple="false"
                    icon
                    class="interest-btn"
                    id="interest-btn"
                    v-if="!interested"
                    @click="markInterested(aptData.aptCode)"
                  >
                    <v-icon>mdi-heart-outline</v-icon>
                  </v-btn>
                  <v-btn
                    :ripple="false"
                    icon
                    color="red"
                    class="interest-btn"
                    id="interest-btn"
                    v-else
                    @click="unmarkInterested(aptData.aptCode)"
                  >
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>
                </div>
              </div>
              {{ this.aptData.aptName }}
            </v-card-title>
            <v-card-subtitle class="text-left subtitle-2 pb-0">| {{ this.aptData.buildYear }}년 준공</v-card-subtitle>
            <v-card-text class="text-left subtitle-2">| {{ this.address }} </v-card-text>
          </v-card>
        </v-col>
      </v-row>
      <v-row dense>
        <v-col>
          <v-card color="primary" dark>
            <v-card-title> 로드뷰 </v-card-title>
            <v-container style="height: 200px" :id="`roadview${this.rvIndexProp}`"></v-container>
          </v-card>
        </v-col>
      </v-row>

      <v-row dense>
        <v-col>
          <v-card color="primary">
            <v-card-title class="white--text"> 면적별 거래 정보 </v-card-title>
            <v-tabs v-model="tab" show-arrows>
              <v-tab v-for="detail in aptData.details" :key="detail.area">
                {{ detail.area }}
              </v-tab>
            </v-tabs>
            <v-tabs-items v-model="tab">
              <v-tab-item v-for="detail in aptData.details" :key="detail.area">
                <v-row dense>
                  <v-col>
                    <v-card flat>
                      <v-card-title class="text-left h6 font-weight-bold ml-1 pb-0 mb-0">그래프</v-card-title>
                      <trade-side-bar-graph :priceInfoList="detail.priceInfoList"></trade-side-bar-graph>
                    </v-card>
                  </v-col>
                </v-row>
                <v-divider></v-divider>
                <v-row dense>
                  <v-col>
                    <v-card flat>
                      <v-card-title class="text-left h6 font-weight-bold ml-0">거래내역</v-card-title>
                      <v-data-table
                        dense
                        multi-sort
                        :sort-by="['dealYear', 'dealMonth']"
                        :sort-desc="[true, true]"
                        :footer-props="{
                          showFirstLastPage: true,
                          itemsPerPageOptions: [],
                        }"
                        :headers="priceInfoHeaders"
                        :items="detail.priceInfoList"
                        class="ml-2 mr-2"
                      >
                        <template v-slot:[`item.price`]="{ item }">
                          {{ priceFormatterWrapper(item.price) }}
                        </template>
                      </v-data-table>
                    </v-card>
                  </v-col>
                </v-row>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import TradeSideBarGraph from "@/components/trade/TradeSideBarGraph.vue";
import { apiInterest } from "@/api/index.js";
import { priceFormatter } from "@/utils/etc";

const tradeStore = "tradeStore";
const userStore = "userStore";
const apiInterestFunc = apiInterest();

export default {
  components: { TradeSideBarGraph },
  name: "TradeSideBarApt",
  computed: {
    ...mapGetters(tradeStore, ["apt"]),
    ...mapGetters(userStore, ["isLogin"]),
  },
  props: {
    aptProp: Object,
    rvIndexProp: Number,
  },
  data() {
    return {
      roadviewContainer: null,
      roadview: null,
      roadviewClient: null,
      position: null,
      tab: null,
      address: "",
      geocoder: null,
      priceInfoHeaders: [
        { text: "연도", value: "dealYear" },
        { text: "월", value: "dealMonth" },
        { text: "금액 (만 원)", value: "price" },
      ],
      interested: false,
      aptData: null,
    };
  },
  methods: {
    initRoadView() {
      if (window.kakao && window.kakao.maps) {
        console.log("initRoadView1");
        this.roadviewContainer = document.getElementById("roadview" + this.rvIndexProp); //로드뷰를 표시할 div
        console.log(kakao.maps);
        this.roadview = new kakao.maps.Roadview(this.roadviewContainer); //로드뷰 객체
        this.roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
        if (this.aptData) {
          this.setRoadView(this.aptData.lat, this.aptData.lng);
        }
      } else {
        console.log("initRoadView2");
      }

      // if (this.apt.lng != null && this.apt.lat) {
      //   this.position = new window.kakao.maps.LatLng(lat, lng);
      // } else {
      //   this.position = new window.kakao.maps.LatLng(37.501929614341925, 127.03899430413212);
      // }
      // // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      // this.roadviewClient.getNearestPanoId(this.position, 200, (panoId) => {
      //   this.roadview.setPanoId(panoId, this.position);
      //   this.roadview.setViewpoint({ pan: 90, tilt: -15, zoom: -3 }); //panoId와 중심좌표를 통해 로드뷰 실행
      // });
    },
    initGeoCoder() {
      if (window.kakao && window.kakao.maps) {
        this.geocoder = new window.kakao.maps.services.Geocoder();
      } else {
        console.log("initRoadView2");
      }
    },
    setRoadView(lat, lng) {
      console.log("setRoadView");
      this.position = new window.kakao.maps.LatLng(lat, lng);
      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      this.roadviewClient.getNearestPanoId(this.position, 200, (panoId) => {
        this.roadview.setPanoId(panoId, this.position); //panoId와 중심좌표를 통해 로드뷰 실행
        this.roadview.setViewpoint({ pan: 90, tilt: -15, zoom: -3 });
      });
    },
    setAddress(lat, lng) {
      if (this.geocoder == null) {
        console.log("geocoder X");
        this.geocoder = new window.kakao.maps.services.Geocoder();
      }
      this.geocoder.coord2Address(lng, lat, (result, status) => {
        if (status == window.kakao.maps.services.Status.OK) {
          const temp = result[0].road_address;
          if (temp) {
            this.address = temp.address_name;
          } else {
            this.address = "X";
          }
        }
      });
    },
    isInterested(aptCode) {
      apiInterestFunc
        .get(`/${aptCode}`)
        .then(({ data }) => (data.addedDate != null ? (this.interested = true) : (this.interested = false)))
        .catch();
    },
    markInterested(aptCode) {
      apiInterestFunc
        .post(`/${aptCode}`)
        .then(() => alert("관심지역 등록 성공!"))
        .then(() => this.isInterested(aptCode))
        .catch(() => alert("관심지역 등록 실패!"));
    },
    unmarkInterested(aptCode) {
      apiInterestFunc
        .delete(`/${aptCode}`)
        .then(() => alert("관심지역 삭제 성공!"))
        .then(() => this.isInterested(aptCode))
        .catch(() => alert("관심지역 삭제 실패!"));
    },
    priceFormatterWrapper(price) {
      return priceFormatter(price);
    },
    dataInitializer(initParam) {
      this.aptData = initParam;
      this.isInterested(this.aptData.aptCode);
      if (this.roadview) {
        this.setRoadView(this.aptData.lat, this.aptData.lng);
      }
      this.setAddress(this.aptData.lat, this.aptData.lng);
    },
  },
  mounted() {
    console.log("mounted");
    if (!window.kakao || !window.kakao.maps) {
      console.log("mounted 생성");
      const script = document.createElement("script");
      //   script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + process.env.VUE_APP_KAKAO_MAP_KEY +"&libraries=services,clusterer";
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=980bea8bcd07009e77637df27e1433d7&libraries=services,clusterer";
      /* global kakao */
      script.addEventListener("load", () => {
        console.log("loaded", window.kakao);
        kakao.maps.load(this.initMap);
        this.initRoadView();
      });
      document.head.appendChild(script);
    } else {
      console.log("mounted 객체 이미 존재");
      this.initRoadView();
      this.initGeoCoder();
    }
  },
  updated() {
    // console.log("updated");
    // if (this.roadview === null && this.apt !== null) {
    //   this.dataInitializer(this.apt);
    // }
  },
  created() {
    console.log("created");
    if (this.aptProp != null) {
      this.dataInitializer(this.aptProp);
    } else {
      this.dataInitializer(this.apt);
    }
  },
  watch: {
    apt() {
      if (this.apt !== null) {
        console.log("apt");
        this.dataInitializer(this.apt);
      }
    },
  },
};
</script>

<style scoped>
.interest-btn-container {
  position: absolute;
  right: 4%;
  top: 13%;
}

.interest-btn:hover {
  background-color: transparent !important;
}

.interest-btn::before {
  background-color: transparent !important;
  display: none;
}

.v-data-table >>> thead > tr > th {
  font-size: 1.1rem !important;
  vertical-align: middle;
  text-align: center !important;
  justify-content: space-between;
}

.v-data-table >>> tbody > tr > td {
  font-size: 0.9rem !important;
  vertical-align: middle;
  text-align: center !important;
}
</style>
