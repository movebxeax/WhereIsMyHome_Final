<template>
  <v-app id="app">
    <the-header-navbar class="navbar"></the-header-navbar>
    <router-view />
    <the-footer></the-footer>
  </v-app>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar.vue";
import TheFooter from "@/components/TheFooter.vue";
export default {
  name: "App",
  components: {
    TheHeaderNavbar,
    TheFooter,
  },
};
</script>

<style lang="scss">
@import url("https://webfontworld.github.io/NanumSquareNeo/NanumSquareNeo.css");

#app {
  font-family: "NanumSquareNeo";
  font-style: normal;
  font-display: swap;
}

.navbar {
  height: 60px;
}
</style>
