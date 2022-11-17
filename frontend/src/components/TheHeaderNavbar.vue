<template>
  <header>
    <v-toolbar elevation="4">
      <v-toolbar-title>
        <router-link to="/" tag="span" style="cursor: pointer">
          {{ appTitle }}
        </router-link>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn text v-for="item in menuItems" :key="item.title" :to="item.path">
          <v-icon left dark>{{ item.icon }}</v-icon>
          {{ item.title }}
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
  </header>
</template>

<script>
export default {
  name: "TheHeaderNavbar",
  data() {
    return {
      appTitle: "HappyHouse",
      menuItems: [
        { title: "Home", path: "/home", icon: "mdi-home" },
        { title: "공지사항", path: "/notice", icon: "mdi-clipboard-alert" },
        { title: "Q&A", path: "/qna", icon: "mdi-help" },
      ],
    };
  },
};
</script>

<style></style>
