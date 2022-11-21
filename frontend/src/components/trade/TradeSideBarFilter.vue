<template>
  <v-container>
    <!-- 검색 창 -->
    <v-container>
      <v-row>
        <v-autocomplete
          v-model="searchSelectedDongcode"
          :items="searchOptions"
          :loading="isLoading"
          :search-input.sync="searchKeyword"
          clearable
          hide-details
          hide-selected
          item-text="address"
          item-value="dongcode"
          prepend-icon="mdi-magnify"
          label="동 검색"
          solo
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
        </v-autocomplete>
      </v-row>
    </v-container>
    <v-divider></v-divider>
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
        <v-card flat>
          <v-card-text>가격 (억 원)</v-card-text>
          <v-row>
            <v-col>
              <v-text-field
                :value="this.minPrice"
                class="mt-0 pt-0"
                single-line
                type="number"
                @change="$set(range, 0, $event)"
                step="0.1"
              ></v-text-field
            ></v-col>
            <v-col>
              <v-text-field
                :value="this.maxPrice"
                class="mt-0 pt-0"
                single-line
                type="number"
                step="0.1"
                @change="$set(range, 0, $event)"
              ></v-text-field
            ></v-col>
          </v-row>
          <v-row>
            <v-col class="px-4">
              <v-range-slider v-model="price.range" :min="price.min" :max="price.max" step="10000000"> </v-range-slider>
            </v-col>
          </v-row>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-card-text>면적(m2)</v-card-text>
          <v-col class="px-4">
            <v-range-slider v-model="area.range" :min="area.min" :max="area.max" step="10">
              <template v-slot:prepend>
                <v-text-field
                  :value="area.range[0]"
                  class="mt-0 pt-0"
                  hide-details
                  single-line
                  type="number"
                  style="width: 60px"
                  @change="$set(range, 0, $event)"
                ></v-text-field>
              </template>
              <template v-slot:append>
                <v-text-field
                  :value="area.range[1]"
                  class="mt-0 pt-0"
                  hide-details
                  single-line
                  type="number"
                  style="width: 60px"
                  @change="$set(range, 0, $event)"
                ></v-text-field>
              </template>
            </v-range-slider>
          </v-col>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-card-text>준공연도</v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
    <v-divider></v-divider>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

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
        { title: "준공년도", value: "buildyear" },
      ],
      dealyear: { min: 2010, max: 2022, range: [2010, 2022] },
      area: { min: 0, max: 200, range: [50, 100] },
      price: { min: 0, max: 3000000000, range: [100_000_000, 1500000000] },

      isLoading: false,
      searchKeyword: null,
      searchSelectedDongcode: null,
    };
  },
  computed: {
    ...mapState(tradeStore, ["searchOptions", "apts"]),
    minPrice() {
      return this.price.range[0] / 100000000;
    },
    maxPrice() {
      return this.price.range[1] / 100000000;
    },
  },
  watch: {
    searchKeyword() {
      if (this.searchOptions.length > 0) return;

      this.isLoading = true;

      this.getSearchOptionList(this.searchKeyword).finally(() => (this.isLoading = false));
    },
    searchSelectedDongcode() {
      if (this.searchSelectedDongcode == null) {
        //   this.clearAptList();
        return;
      }

      this.setDong(this.searchOptions.find((dong) => dong.dongcode === this.searchSelectedDongcode));
      this.getAptList(this.searchSelectedDongcode);
    },
  },
  methods: {
    ...mapActions(tradeStore, ["getSearchOptionList", "getAptList", "clearAptList", "setDong"]),
  },
  created() {
    // this.getAptList("1168010100");
  },
};
</script>

<style></style>
