<template>
  <v-card class="card-main mt-4">
    <div class="interest-card-root d-flex">
      <v-card class="interest-card ma-2" v-for="interest in interests" :key="interest.aptName">
        <div class="interest-card-content">
          <trade-side-bar-apt class="interest-card-apt" :aptProp="interest"></trade-side-bar-apt>
        </div>
      </v-card>
    </div>
  </v-card>
</template>

<script>
import TradeSideBarApt from "@/components/trade/TradeSideBarApt.vue";
import { apiInterest } from "@/api/index";
import { mapActions } from "vuex";

const tradeStore = "tradeStore";
const apiInterestFunc = apiInterest();

export default {
  components: {
    TradeSideBarApt,
  },
  created() {
    apiInterestFunc
      .get("")
      .then(({ data }) => (this.interests = data))
      .catch(() => alert("관심목록을 얻어오는데 실패했습니다."));
  },
  data() {
    return {
      interests: [],
      tab: 0,
    };
  },
  methods: {
    ...mapActions(tradeStore, ["setApt"]),
  },
  watch: {
    tab(newValue) {
      this.setApt(this.interests[newValue]);
    },
  },
};
</script>

<style scoped>
.card-main {
  max-height: 80vh;
  overflow-y: hidden;
  overflow-x: scroll;
}
.interest-card {
  max-width: 25% !important;
  min-width: 25% !important;
}

.interest-card-content {
  max-height: 75vh;
  overflow-y: scroll !important;
}

.interest-card-content::-webkit-scrollbar {
  width: 0px;
  height: 0px;
}
</style>
