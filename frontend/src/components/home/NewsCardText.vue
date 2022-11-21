<template>
  <div>
    <v-card-text class="pa-2 ma-1 text-left" v-for="(newsItem, index) in newsList" :key="index">
      <div class="d-flex">
        <div>
          <v-btn text color="#3F72AF" @click="loadNoticeArticle(newsItem.no)">
            {{ newsItem.subject }}
          </v-btn>
        </div>
        <div class="pr-4 ml-auto">
          {{ newsItem.date }}
        </div>
      </div>
    </v-card-text>
    <v-card-text class="pa-2 ma-1 text-left" v-for="i in 5 - Object.keys(newsList).length" :key="i">
      <div class="d-flex">
        <div>
          <v-btn text disabled color="#3F72AF"> </v-btn>
        </div>
        <div class="pr-4 ml-auto"></div>
      </div>
    </v-card-text>
  </div>
</template>

<script>
//import { httpNotice } from "@/utils/api.js";
export default {
  name: "NewsCardText",
  data() {
    return {
      newsList: {},
    };
  },
  created() {
    //httpNotice.get("").then(({ data }) => (this.noticeList = data));
  },
  methods: {
    // loadNoticeArticle(articleno) {
    //   this.$router.push(`/notice/detail/${articleno}`);
    // },
  },
};
</script>

<style></style>
