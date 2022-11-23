<template>
  <div class="side-bar-wrapper">
    <template v-if="isShow">
      <div class="side-bar">
        <trade-side-bar-filter
          @changeCenterBySearch="changeCenterBySearch"
          class="ml-2 mr-2 mt-2"
        ></trade-side-bar-filter>
        <trade-side-bar-apt v-if="apt"></trade-side-bar-apt>
      </div>
    </template>
    <button id="trade-side-bar-active-btn" @click="toggleSide">
      <v-icon center color="black" v-if="isShow">mdi-chevron-left</v-icon>
      <v-icon center color="black" v-else>mdi-chevron-right</v-icon>
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
  props: ["map"],
  computed: {
    ...mapGetters(tradeStore, ["apt"]),
  },
  methods: {
    toggleSide() {
      this.isShow = !this.isShow;
      this.$emit("mapRelayout");
    },
    showSide() {
      this.isShow = true;
      this.$emit("mapRelayout");
    },
    changeCenterBySearch() {
      this.$emit("changeCenterBySearch");
    },
  },
  watch: {
    apt() {
      this.showSide();
    },
  },
};
</script>

<style lang="scss" scoped>
.side-bar-wrapper {
  background-color: white;
  overflow: scroll;

  .side-bar {
    opacity: 90%;
    width: 400px;
  }
}

#trade-side-bar-active-btn {
  position: absolute;
  top: 350px;
  padding: 0 0 0 0;
  background-color: white;
  width: 30px;
  height: 50px;
  left: -30px;
  // height: 30px;
  // border: 1px solid aliceblue;
  border-bottom: 1px solid gray;
  border-top: 1px solid gray;
  border-left: 1px solid gray;
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}
</style>
