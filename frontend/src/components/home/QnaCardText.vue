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
    <div v-if="Object.keys(qnaList)">
      <v-card-text class="pa-2 ma-1 text-left" v-for="i in 5 - Object.keys(qnaList).length" :key="i">
        <div class="d-flex">
          <div>
            <v-btn text disabled color="#3F72AF"> </v-btn>
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
import { apiQna } from "@/api/index.js";

const httpQna = apiQna();

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
