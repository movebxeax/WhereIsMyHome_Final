<template>
  <div class="side-bar-wrapper">
    <template v-if="isShow">
      <div class="side-bar">
        <trade-side-bar-filter></trade-side-bar-filter>
        <trade-side-bar-apt v-if="apt != null"></trade-side-bar-apt>
      </div>
    </template>
    <button id="trade-side-bar-active-btn" @click="toggleSide">
      <v-icon center v-if="isShow">mdi-chevron-left</v-icon>
      <v-icon center v-else>mdi-chevron-right</v-icon>
    </button>
  </div>
</template>

<script>
import TradeSideBarFilter from "@/components/trade/TradeSideBarFilter.vue";
import { mapGetters } from "vuex";
import TradeSideBarApt from "@/components/trade/TradeSideBarApt.vue";
const tradeStore = "tradeStore";

export default {
  name: "TradeSideBar",
  components: {
    TradeSideBarFilter,
    TradeSideBarApt,
  },
  data() {
    return {
      isShow: true,
    };
  },
  computed: {
    ...mapGetters(tradeStore, ["apt"]),
  },
  methods: {
    toggleSide() {
      this.isShow = !this.isShow;
    },
    showSide() {
      this.isShow = true;
    },
  },
  watch: {
    apt() {
      console.log(this.apt);
      this.showSide();
    },
  },
};
</script>

<style lang="scss" scoped>
.side-bar-wrapper {
  background-color: white;
  overflow-y: auto;
  overflow-x: auto;
  .side-bar {
    opacity: 90%;
    width: 400px;
    // position: relative;
    // height: 100%;
    // display: flex;
  }
}

#trade-side-bar-active-btn {
  position: absolute;
  top: 350px;
  padding: 0 0 0 0;
  background-color: white;
  width: 30px;
  height: 50px;
  right: -30px;
  // height: 30px;
  // border: 1px solid aliceblue;
  border-bottom: 1px solid grey;
  border-top: 1px solid grey;
  border-right: 1px solid grey;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}
</style>
