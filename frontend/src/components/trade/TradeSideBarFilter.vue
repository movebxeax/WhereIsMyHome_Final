<template>
  <div>
    <v-container>
      <!-- 검색 창 -->
      <v-row dense>
        <v-col>
          <v-row align="center">
            <v-col class="pa-0 mr-2" cols="10">
              <v-autocomplete
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
                label="검색어를 입력하세요"
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
                  <v-chip
                    v-bind="attr"
                    :input-value="selected"
                    color="blue-grey"
                    class="my-chip-class white--text"
                    v-on="on"
                    justify
                  >
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
                  <!-- 주변 정보  필터 -->
                  <!-- 은행, 마트, 약국, 주유소, 카페, 편의점 -->
                  <v-card flat>
                    <v-card-text>
                      <v-row align="center" justify="center">
                        <v-btn-toggle v-model="selectedCategories" multiple>
                          <v-btn
                            v-for="(item, index) in categoryItems"
                            id="item.id"
                            :color="setCategoryBtnColor(index)"
                            :key="index"
                            @click="toggleCategoryColor(index)"
                          >
                            <v-container>
                              <v-row class="pa-0 ma-0">
                                <v-col>
                                  <v-icon :color="setCategoryIconColor(index)">
                                    {{ item.icon }}
                                  </v-icon>
                                </v-col>
                              </v-row>
                              <v-row class="pa-0 ma-0">
                                <v-col style="font-size: 11px; font-weight: bold">
                                  <div>
                                    {{ item.name }}
                                  </div>
                                </v-col>
                              </v-row>
                            </v-container>
                          </v-btn>
                        </v-btn-toggle>
                      </v-row>
                    </v-card-text>
                  </v-card>
                  <!-- 필터 탭-->
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
                            <v-text-field
                              :value="this.minPrice"
                              class="mt-0 pt-0"
                              single-line
                              type="number"
                              @change="$set(curFilterOptions.price.range, 0, $event)"
                              step="0.1"
                            ></v-text-field>
                          </v-col>
                          <v-col>
                            <v-text-field
                              :value="this.maxPrice"
                              class="mt-0 pt-0"
                              single-line
                              type="number"
                              step="0.1"
                              @change="$set(curFilterOptions.price.range, 0, $event)"
                            ></v-text-field>
                          </v-col>
                        </v-row>
                        <v-row>
                          <v-col class="px-4">
                            <v-range-slider
                              v-model="curFilterOptions.price.range"
                              :min="curFilterOptions.price.min"
                              :max="curFilterOptions.price.max"
                              step="10000000"
                            >
                            </v-range-slider>
                          </v-col>
                        </v-row>
                      </v-card>
                    </v-tab-item>
                    <v-tab-item>
                      <v-card>
                        <v-card-text>면적(m2)</v-card-text>
                        <v-col class="px-4">
                          <v-range-slider
                            v-model="curFilterOptions.area.range"
                            :min="curFilterOptions.area.min"
                            :max="curFilterOptions.area.max"
                            step="10"
                          >
                            <template v-slot:prepend>
                              <v-text-field
                                :value="curFilterOptions.area.range[0]"
                                class="mt-0 pt-0"
                                hide-details
                                single-line
                                type="number"
                                style="width: 60px"
                                @change="$set(curFilterOptions.area.range, 0, $event)"
                              ></v-text-field>
                            </template>
                            <template v-slot:append>
                              <v-text-field
                                :value="curFilterOptions.area.range[1]"
                                class="mt-0 pt-0"
                                hide-details
                                single-line
                                type="number"
                                style="width: 60px"
                                @change="$set(curFilterOptions.area.range, 0, $event)"
                              ></v-text-field>
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
                                <v-radio
                                  v-for="(item, index) in buildyearItems"
                                  :key="index"
                                  :label="item.label"
                                  :color="item.color"
                                  :value="item.value"
                                ></v-radio>
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
      categoryItems: [
        {
          icon: "mdi-bank",
          id: "BK9",
          name: "은행",
        },
        {
          icon: "mdi-cart",
          id: "MT1",
          name: "마트",
        },
        {
          icon: "mdi-pill",
          id: "PM9",
          name: "약국",
        },
        {
          icon: "mdi-barrel",
          id: "OL7",
          name: "주유소",
        },
        {
          icon: "mdi-coffee",
          id: "CE7",
          name: "카페",
        },
        {
          icon: "mdi-store-24-hour",
          id: "CS2",
          name: "편의점",
        },
      ],
      isLoading: false,
      searchKeyword: null,
      curFilterOptions: {
        area: { min: 0, max: 200, range: [0, 200] },
        price: { min: 0, max: 5000000000, range: [0, 5000000000] },
        buildYear: 100,
      },
      selectedCategories: [],
      isSelectedCategories: [false, false, false, false, false, false],
    };
  },
  computed: {
    ...mapGetters(tradeStore, ["searchOptions", "apts", "inputDongcode", "dong"]),
    minPrice() {
      return this.curFilterOptions.price.range[0] / 100000000;
    },
    maxPrice() {
      return this.curFilterOptions.price.range[1] / 100000000;
    },
  },
  watch: {
    searchKeyword() {
      if (!this.searchKeyword) return;

      this.isLoading = true;
      this.getSearchOptionList(this.searchKeyword).finally(() => (this.isLoading = false));
    },
    curFilterOptions: {
      deep: true,
      handler() {
        this.setFilterOptions(this.curFilterOptions);
      },
    },
    selectedCategories() {
      this.$emit("changeCategories", this.selectedCategories);
    },
  },
  methods: {
    ...mapActions(tradeStore, [
      "getSearchOptionList",
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

      // 좌표 이동으로 리스트 조회하도록 함
      this.setDong(this.searchOptions.find((dong) => dong.dongcode == this.inputDongcode));
      this.$emit("changeCenterBySearch");
    },
    toggleCategoryColor(index) {
      this.isSelectedCategories[index] = !this.isSelectedCategories[index];
    },
    setCategoryBtnColor(index) {
      return this.isSelectedCategories[index] ? "primary" : "white";
    },
    setCategoryIconColor(index) {
      return this.isSelectedCategories[index] ? "white" : "primary";
    },
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
