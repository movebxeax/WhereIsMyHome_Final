<template>
  <div>
    <v-card-text class="pa-2 ma-1 text-left" v-for="(qnaItem, index) in qnaList" :key="index">
      <div class="d-flex">
        <div>
          <v-btn text color="#3F72AF" @click="loadQnaArticle(qnaItem.no)">
            {{ qnaItem.title }}
          </v-btn>
        </div>
        <div class="pr-4 ml-auto">
          {{ qnaItem.date }}
        </div>
      </div>
    </v-card-text>
  </div>
</template>

<script>
import { httpQna } from "@/utils/api.js";
export default {
  name: "QnaCardText",
  data() {
    return {
      qnaList: {},
    };
  },
  created() {
    httpQna.get("?limit=5").then(({ data }) => (this.qnaList = data));
  },
  methods: {
    loadQnaArticle(articleno) {
      this.$router.push(`/qna/detail/${articleno}`);
    },
  },
};
</script>

<style></style>
