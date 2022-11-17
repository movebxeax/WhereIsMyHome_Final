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

        <!-- 로그인 O -->
        <v-menu offset-y v-if="userInfo">
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-bind="attrs" v-on="on">
              <v-icon left dark>mdi-account-circle</v-icon>
              {{ userInfo.username }} 님
            </v-btn>
          </template>
          <v-list>
            <v-list-item link>
              <v-list-item-title>마이 페이지</v-list-item-title>
            </v-list-item>
            <v-list-item link>
              <v-list-item-title>로그아웃</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>

        <!-- 로그인 X -->
        <v-menu offset-y v-else>
          <template v-slot:activator="{ on, attrs }">
            <v-btn id="login" v-bind="attrs" v-on="on">
              <v-icon dark large>mdi-account-circle</v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-item link>
              <v-list-item-title>회원 가입</v-list-item-title>
            </v-list-item>
            <v-list-item link>
              <v-list-item-title>로그인</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-toolbar-items>
    </v-toolbar>
  </header>
</template>

<script>
import { mapGetters } from "vuex";
const userStore = "userStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {
      appTitle: "HappyHouse",
      menuItems: [
        { title: "Home", path: "/", icon: "mdi-home" },
        { title: "아파트 거래 정보", path: "/trade", icon: "mdi-home-modern" },
        { title: "공지사항", path: "/notice", icon: "mdi-clipboard-alert" },
        { title: "Q&A", path: "/qna", icon: "mdi-help" },
      ],
    };
  },
  computed: {
    ...mapGetters(userStore, ["userInfo"]),
  },
  created() {
    console.log(this.userInfo);
  },
};
</script>

<style></style>
