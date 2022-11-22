<template>
  <div>
    <v-container>
      <v-row dense>
        <v-col>
          <v-card dark>
            <v-card-title>{{ this.apt.aptName }}</v-card-title>
            <v-card-subtitle class="text-left subtitle-2 pb-0">| {{ this.apt.buildYear }}년 준공</v-card-subtitle>
            <v-card-text class="text-left subtitle-2">| {{ this.address }} </v-card-text>
          </v-card>
        </v-col>
      </v-row>
      <v-row dense>
        <v-col>
          <v-card color="primary" dark>
            <v-card-title> 로드뷰 </v-card-title>
            <v-container style="height: 200px" id="roadview"></v-container>
          </v-card>
        </v-col>
      </v-row>

      <v-row dense>
        <v-col>
          <v-card color="primary" dark>
            <v-card-title> 면적별 거래 정보 </v-card-title>
            <v-tabs v-model="tab" dark show-arrows>
              <v-tab v-for="detail in apt.details" :key="detail.area">
                {{ detail.area }}
              </v-tab>
            </v-tabs>

            <v-tabs-items v-model="tab">
              <v-tab-item v-for="detail in apt.details" :key="detail.area">
                <v-row dense>
                  <v-col>
                    <v-card>
                      <v-card-title class="text-left h6 font-weight-bold"> 그래프 </v-card-title>
                      <v-spacer></v-spacer>
                      <trade-side-bar-graph :priceInfoList="detail.priceInfoList"></trade-side-bar-graph>
                    </v-card>
                  </v-col>
                </v-row>
                <v-row dense>
                  <v-col>
                    <v-card>
                      <v-card-title class="text-left h6 font-weight-bold"> 거래내역 </v-card-title>
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
                      ></v-data-table>
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

const tradeStore = "tradeStore";
export default {
  components: { TradeSideBarGraph },
  name: "TradeSideBarApt",
  computed: {
    ...mapGetters(tradeStore, ["apt"]),
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
    };
  },
  methods: {
    initRoadView(lat, lng) {
      if (window.kakao && window.kakao.maps) {
        this.roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
        this.roadview = new window.kakao.maps.Roadview(this.roadviewContainer); //로드뷰 객체
        this.roadviewClient = new window.kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
        if (this.apt.lng != null && this.apt.lat) {
          this.position = new window.kakao.maps.LatLng(lat, lng);
        } else {
          this.position = new window.kakao.maps.LatLng(37.501929614341925, 127.03899430413212);
        }
        // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
        this.roadviewClient.getNearestPanoId(this.position, 200, (panoId) => {
          this.roadview.setPanoId(panoId, this.position);
          this.roadview.setViewpoint({ pan: 90, tilt: -15, zoom: -3 }); //panoId와 중심좌표를 통해 로드뷰 실행
        });
      }
    },
    setRoadView(lat, lng) {
      this.position = new window.kakao.maps.LatLng(lat, lng);
      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      this.roadviewClient.getNearestPanoId(this.position, 200, (panoId) => {
        this.roadview.setPanoId(panoId, this.position); //panoId와 중심좌표를 통해 로드뷰 실행
        this.roadview.setViewpoint({ pan: 90, tilt: -15, zoom: -3 });
      });
    },
    setAddress(lat, lng) {
      if (this.geocoder == null) {
        this.geocoder = new window.kakao.maps.services.Geocoder();
      }
      this.geocoder.coord2Address(lng, lat, (result, status) => {
        if (status == window.kakao.maps.services.Status.OK) {
          const temp = result[0].road_address;
          this.address = temp.address_name;
        }
      });
    },
  },
  updated() {
    if (this.roadview === null && this.apt !== null) {
      this.initRoadView(this.apt.lat, this.apt.lng);
      this.setAddress(this.apt.lat, this.apt.lng);
    }
  },
  watch: {
    apt() {
      if (this.apt !== null) {
        this.setRoadView(this.apt.lat, this.apt.lng);
        this.setAddress(this.apt.lat, this.apt.lng);
      }
    },
  },
};
</script>

<style></style>
