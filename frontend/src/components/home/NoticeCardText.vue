<template>
  <div>
    <v-card-text class="pa-2 ma-1 text-left" v-for="(noticeItem, index) in noticeList" :key="index">
      <div class="d-flex">
        <div>
          <v-btn text color="#3F72AF" @click="loadNoticeArticle(noticeItem.no)">
            {{ noticeItem.title }}
          </v-btn>
        </div>
        <div class="pr-4 ml-auto">
          {{ noticeItem.date }}
        </div>
      </div>
    </v-card-text>
    <div v-if="Object.keys(noticeList)">
      <v-card-text class="pa-2 ma-1 text-left" v-for="i in 5 - Object.keys(noticeList).length" :key="i">
        <div class="d-flex">
          <div>
            <v-btn text disabled color="#3F72AF"></v-btn>
          </div>
          <div class="pr-4 ml-auto"></div>
        </div>
      </v-card-text>
    </div>
    <div v-else>
      <v-card-text class="pa-2 ma-1 text-left" v-for="i in 5" :key="i">
        <div class="d-flex">
          <div>
            <v-btn text disabled color="#3F72AF"> </v-btn>
          </div>
          <div class="pr-4 ml-auto"></div>
        </div>
      </v-card-text>
    </div>
  </div>
</template>

<script>
import { apiNotice } from "@/api/index";

const httpNotice = apiNotice();

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
