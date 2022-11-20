<template>
  <v-container>
    <v-row class="mb-1">
      <v-col>
        <v-card>
          <v-card-title>{{ qna.title }}</v-card-title>
          <v-card-text>
            <v-row>
              <v-divider></v-divider>
            </v-row>
            <v-row>
              <h4>작성자: {{ qna.author }}</h4>
            </v-row>
            <v-row>
              <h4>작성일: {{ qna.date }}</h4>
            </v-row>
            <v-row>
              <h4>조회수: {{ qna.viewCount }}</h4>
            </v-row>
            <v-row>
              <v-divider></v-divider>
            </v-row>
            <v-row>
              <v-textarea v-html="message" disabled rows="10" max-rows="30"></v-textarea>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-col class="text-left">
        <v-btn depressed @click="moveList">목록</v-btn>
      </v-col>
      <v-col class="text-right">
        <v-btn class="mr-4" color="primary" depressed @click="moveModify">수정</v-btn>
        <v-btn class="mr-4" color="error" depressed @click="deleteQna">삭제</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { apiQna } from "@/api/index";

export default {
  name: "QnaDetail",
  props: ["no"],
  data() {
    return {
      qna: {},
    };
  },
  computed: {
    message() {
      if (this.qna.content) return this.qna.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    this.getQna();
  },
  methods: {
    getQna() {
      apiQna.get(`/${this.no}`).then(({ data }) => {
        this.qna = data;
      });
    },
    moveList() {
      this.$router.push("/qna");
    },
    moveModify() {
      this.$router.push(`/qna/modify/${this.no}`);
    },
    deleteQna() {
      if (confirm("삭제하시겠습니까?")) {
        apiQna.delete(`/${this.qna.no}`).then(({ status }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (status == 200) {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
      }
    },
  },
};
</script>

<style></style>
