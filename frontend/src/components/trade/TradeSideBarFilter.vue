<template>
  <div>
    <!-- 검색 창 -->
    <v-container>
      <v-row dense>
        <v-col>
          <v-row align="center">
            <v-col class="pa-0 mr-2" cols="10">
              <v-autocomplete :value="inputDongcode" @input="setInputDongcode" :items="searchOptions"
                :loading="isLoading" :search-input.sync="searchKeyword" clearable hide-details hide-selected
                item-text="address" item-value="dongcode" label="검색어를 입력하세요" solo rounded>
                <template v-slot:no-data>
                  <v-list-item>
                    <v-list-item-title>
                      검색어를 입력하세요.
                      <strong> (역삼동, 종로구)</strong>
                    </v-list-item-title>
                  </v-list-item>
                </template>
                <template v-slot:selection="{ attr, on, item, selected }">
                  <v-chip v-bind="attr" :input-value="selected" color="blue-grey" class="my-chip-class white--text"
                    v-on="on" justify>
                    <span v-text="item.address"></span>
                  </v-chip>
                </template>
                <template v-slot:item="{ item }">
                  <v-list-item-content>
                    <v-list-item-title v-text="item.address"></v-list-item-title>
                  </v-list-item-content>
                </template>
              </v-autocomplete>
            </v-col>
            <v-col cols="1" class="pa-0 ma-0">
              <v-btn small rounded @click="onSearch">
                <v-icon color="primary">mdi-magnify</v-icon>
              </v-btn>
            </v-col>
          </v-row>
          <!-- <v-autocomplete
              :value="inputDongcode"
              @input="setInputDongcode"
              :items="searchOptions"
              :loading="isLoading"
              :search-input.sync="searchKeyword"
              clearable
              hide-details
              hide-selected
              item-text="address"
              item-value="dongcode"
              label="가격이 궁금한 지역을 입력해보세요."
              solo
              rounded
            >
              <template v-slot:no-data>
                <v-list-item>
                  <v-list-item-title>
                    검색어를 입력하세요.
                    <strong> (역삼동, 종로구)</strong>
                  </v-list-item-title>
                </v-list-item>
              </template>
              <template v-slot:selection="{ attr, on, item, selected }">
                <v-chip v-bind="attr" :input-value="selected" color="blue-grey" class="white--text" v-on="on">
                  <span v-text="item.address"></span>
                </v-chip>
              </template>
              <template v-slot:item="{ item }">
                <v-list-item-content>
                  <v-list-item-title v-text="item.address"></v-list-item-title>
                </v-list-item-content>
              </template>
              <template v-slot:append>
                <v-slide-x-reverse-transition mode="out-in">
                  <v-icon color="primary" @click="onSearch">mdi-magnify</v-icon>
                </v-slide-x-reverse-transition>
              </template>
            </v-autocomplete> -->
        </v-col>
      </v-row>

      <v-row dense>
        <v-col>
          <v-card>
            <v-expansion-panels>
              <v-expansion-panel>
                <v-expansion-panel-header class="font-weight-bold">
                  <div class="filter-icon">
                    <v-icon>mdi-filter</v-icon>
                  </div>
                  <div class="filter-text">필터</div>
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                  <!-- 필터 -->
                  <v-tabs fixed-tabs v-model="filterTab">
                    <v-tabs-slider color="primary"></v-tabs-slider>
                    <!-- <v-tab v-for="(item, index) in filterTabItems" :key="index">{{ item.title }}</v-tab> -->
                    <v-tab> {{ filterTabItems[0].title }} </v-tab>
                    <v-tab> {{ filterTabItems[1].title }} </v-tab>
                    <v-tab> {{ filterTabItems[2].title }} </v-tab>
                  </v-tabs>

                  <!--필터 아이템-->
                  <v-tabs-items v-model="filterTab">
                    <v-tab-item>
                      <v-card>
                        <v-card-text>가격 (억 원)</v-card-text>
                        <v-row>
                          <v-col>
                            <v-text-field :value="this.minPrice" class="mt-0 pt-0" single-line type="number"
                              @change="$set(curFilterOptions.price.range, 0, $event)" step="0.1"></v-text-field>
                          </v-col>
                          <v-col>
                            <v-text-field :value="this.maxPrice" class="mt-0 pt-0" single-line type="number" step="0.1"
                              @change="$set(curFilterOptions.price.range, 0, $event)"></v-text-field>
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col class="px-4">
                            <v-range-slider v-model="curFilterOptions.price.range" :min="curFilterOptions.price.min"
                              :max="curFilterOptions.price.max" step="10000000">
                            </v-range-slider>
                          </v-col>
                        </v-row>
                      </v-card>
                    </v-tab-item>
                    <v-tab-item>
                      <v-card>
                        <v-card-text>면적(m2)</v-card-text>
                        <v-col class="px-4">
                          <v-range-slider v-model="curFilterOptions.area.range" :min="curFilterOptions.area.min"
                            :max="curFilterOptions.area.max" step="10">
                            <template v-slot:prepend>
                              <v-text-field :value="curFilterOptions.area.range[0]" class="mt-0 pt-0" hide-details
                                single-line type="number" style="width: 60px"
                                @change="$set(curFilterOptions.area.range, 0, $event)"></v-text-field>
                            </template>
                            <template v-slot:append>
                              <v-text-field :value="curFilterOptions.area.range[1]" class="mt-0 pt-0" hide-details
                                single-line type="number" style="width: 60px"
                                @change="$set(curFilterOptions.area.range, 0, $event)"></v-text-field>
                            </template>
                          </v-range-slider>
                        </v-col>
                      </v-card>
                    </v-tab-item>
                    <v-tab-item>
                      <v-card>
                        <v-card-text>준공연도 </v-card-text>
                        <v-container fluid>
                          <v-row>
                            <v-col cols="4" sm="6" md="6">
                              <v-radio-group v-model="curFilterOptions.buildYear" column>
                                <v-radio v-for="(item, index) in buildyearItems" :key="index" :label="item.label"
                                  :color="item.color" :value="item.value"></v-radio>
                              </v-radio-group>
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card>
                    </v-tab-item>
                  </v-tabs-items>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

const tradeStore = "tradeStore";

export default {
  name: "TradeSideBarFilter",
  data() {
    return {
      filterTab: null,
      filterTabItems: [
        // { title: "거래연도", value: "dealyear" },
        { title: "가격", value: "price" },
        { title: "면적", value: "area" },
        { title: "준공년도", value: "buildYear" },
      ],

      buildyearItems: [
        { label: "전체", color: "primary", value: 100 },
        { label: "1년 이내", color: "success", value: 1 },
        { label: "5년 이내", color: "info", value: 5 },
        { label: "10년 이내", color: "warning", value: 10 },
        { label: "20년 이내", color: "secondary", value: 20 },
        { label: "30년 이내", color: "error", value: 30 },
      ],
      isLoading: false,
      searchKeyword: null,
      curFilterOptions: {
        area: { min: 0, max: 200, range: [0, 200] },
        price: { min: 0, max: 5000000000, range: [0, 5000000000] },
        buildYear: 100,
      },
    };
  },
  computed: {
    ...mapGetters(tradeStore, ["searchOptions", "apts", "inputDongcode"]),
    minPrice() {
      return this.curFilterOptions.price.range[0] / 100000000;
    },
    maxPrice() {
      return this.curFilterOptions.price.range[1] / 100000000;
    },
  },
  watch: {
    searchKeyword() {
      if (this.searchOptions.length > 0) return;

      this.isLoading = true;

      this.getSearchOptionList(this.searchKeyword).finally(() => (this.isLoading = false));
    },

    curFilterOptions: {
      deep: true,
      handler() {
        console.log(this.curFilterOptions.buildYear);
        console.log("setFilter Call");
        this.setFilterOptions(this.curFilterOptions);
      },
    },
  },
  methods: {
    ...mapActions(tradeStore, [
      "getSearchOptionList",
      "getAptList",
      "clearAptList",
      "setDong",
      "setFilterOptions",
      "setInputDongcode",
    ]),
    onSearch() {
      if (!this.inputDongcode) {
        alert("검색어를 입력하세요.");
        return;
      }
      this.setDong(this.searchOptions.find((dong) => dong.dongcode === this.inputDongcode));
      this.getAptList(this.inputDongcode);
    },
  },
  created() {
    // if (this.curFilterOptions == null) {
    //   this.curFilterOptions = {
    //     area: { min: 0, max: 200, range: [0, 200] },
    //     price: { min: 0, max: 5000000000, range: [0, 5000000000] },
    //     buildYear: 100,
    //   };
    // }
  },
};
</script>

<style lang="scss" scoped>
.v-input__prepend-outer {
  margin-left: 1000px !important;
}

.filter-icon {
  position: absolute;
  left: 10px;
}

.filter-text {
  position: relative;
  left: 8%;
}

.my-chip-class {
  background: transparent !important;
  color: black !important;
  min-width: 155px;
}
</style>
