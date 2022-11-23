<template>
  <v-card class="card-main mt-4">
    <div class="interest-card-root d-flex ma-2">
      <v-card class="ma-2" v-for="interest in interests" :key="interest.aptName">
        <div class="interest-card-content">
          <trade-side-bar-apt class="interest-card-apt" :apt="interest"></trade-side-bar-apt>
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
  max-height: 70vh;
  overflow-y: hidden;
  overflow-x: scroll;
}

.interest-card-root {
  max-width: 20%;
}

.interest-card-content {
  max-height: 65vh;
  overflow-y: scroll !important;
}

.interest-card-content::-webkit-scrollbar {
  width: 0px;
  height: 0px;
}
</style>
