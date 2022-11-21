<template>
  <div>
    <v-card-text class="pa-2 ma-1 text-left" v-for="(noticeItem, index) in noticeList" :key="index">
      <div class="d-flex">
        <div>
          <v-btn text color="#3F72AF" @click="loadNoticeArticle(noticeItem.no)">
            {{ noticeItem.subject }}
          </v-btn>
        </div>
        <div class="pr-4 ml-auto">
          {{ noticeItem.date }}
        </div>
      </div>
    </v-card-text>
    <v-card-text class="pa-2 ma-1 text-left" v-for="i in 5 - Object.keys(noticeList).length" :key="i">
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
import { httpNotice } from "@/utils/api.js";
export default {
  name: "NoticeCardText",
  data() {
    return {
      noticeList: {},
    };
  },
  created() {
    httpNotice.get("").then(({ data }) => (this.noticeList = data));
  },
  methods: {
    loadNoticeArticle(articleno) {
      this.$router.push(`/notice/detail/${articleno}`);
    },
  },
};
</script>

<style></style>
