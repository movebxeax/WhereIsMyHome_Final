<template>
  <div class="side-bar-wrapper">
    <template v-if="isShow">
      <div class="side-bar" v-if="isShow"></div>
    </template>
    <v-btn color="primary" class="trade-side-bar-active-btn" @click="showSide">
      {{ isShow ? "닫기" : "열기" }}
    </v-btn>
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
  opacity: 90%;
  .side-bar {
    width: 500px;
    // position: relative;
    // height: 100%;
    // display: flex;
    // justify-content: center;
  }
}

.trade-side-bar-active-btn {
  position: absolute;
  top: 350px;
  right: -65px;
  width: 30px;
  height: 30px;
  // border: 1px solid aliceblue;
  box-sizing: border-box;
  font-weight: 600;
  font-size: 20px;
  color: black;
}
</style>
