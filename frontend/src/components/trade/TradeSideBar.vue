<template>
  <div class="side-bar-wrapper">
    <template v-if="isShow">
      <div class="side-bar"></div>
    </template>
    <button id="trade-side-bar-active-btn" @click="showSide">
      <v-icon v-if="isShow">mdi-chevron-left</v-icon>
      <v-icon v-else>mdi-chevron-right</v-icon>
      <!-- {{ isShow ? "<" : ">" }} -->
    </button>
  </div>
</template>

<script>
export default {
  name: "TradeSideBar",
  data() {
    return {
      isShow: false,
    };
  },
  methods: {
    showSide() {
      this.isShow = !this.isShow;
    },
  },
};
</script>

<style lang="scss" scoped>
.side-bar-wrapper {
  background-color: yellow;
  .side-bar {
    opacity: 90%;
    width: 500px;
    // position: relative;
    // height: 100%;
    // display: flex;
    // justify-content: center;
  }
}

#trade-side-bar-active-btn {
  position: absolute;
  top: 350px;
  padding: 0 0 0 0;
  background-color: white;
  width: 25px;
  height: 40px;
  right: -25px;
  // height: 30px;
  // border: 1px solid aliceblue;
  border-bottom: 1px solid grey;
  border-top: 1px solid grey;
  border-right: 1px solid grey;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}
</style>
