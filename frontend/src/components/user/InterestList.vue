<template>
  <v-card class="card-main mt-4">
    <v-tabs class="pl-4 pr-4 pb-4" v-model="tab">
      <v-tabs-slider color="#000000"></v-tabs-slider>
      <v-tab v-for="interest in interests" :key="interest.aptName">
        <div class="caption py-1">{{ interest.aptName }}</div>
      </v-tab>
      <v-tab-item v-for="interest in interests" :key="interest.aptName" class="pt-2">
        <trade-side-bar-apt :apt="interest"></trade-side-bar-apt>
      </v-tab-item>
    </v-tabs>

  </v-card>
</template>

<script>
import TradeSideBarApt from "@/components/trade/TradeSideBarApt.vue"
import { apiInterest } from "@/api/index";
import { mapActions } from "vuex";

const tradeStore = "tradeStore";
const apiInterestFunc = apiInterest();

export default {
  components: {
    TradeSideBarApt,
  },
  created() {
    apiInterestFunc.get("")
      .then(({ data }) => this.interests = data)
      .catch(() => alert("관심목록을 얻어오는데 실패했습니다."));
  },
  data() {
    return {
      interests: [],
      tab: 0,
    }
  },
  methods: {
    ...mapActions(tradeStore, ["setApt"]),
  },
  watch: {
    tab(newValue) {
      this.setApt(this.interests[newValue]);
    }
  },
}
</script>

<style>
.card-main {
  max-height: 70vh;
  overflow: scroll;
}
</style>