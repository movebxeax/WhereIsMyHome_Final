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
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.navbar {
  height: 60px;
}
</style>
