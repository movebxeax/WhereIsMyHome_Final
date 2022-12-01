<template>
  <v-container class="detail-container">
    <v-row class="mb-1">
      <v-col>
        <v-card outlined>
          <div>
            <v-card-title class="d-flex justify-content ml-1">
              {{ qna.title }}
            </v-card-title>
          </div>
          <div class="ml-3">
            <v-card-subtitle>
              <v-row>
                <v-divider></v-divider>
              </v-row>
              <v-row>
                <v-col class="pl-1" cols="2"> </v-col>
                <v-col cols="2"></v-col>
                <v-col cols="8" class="">
                  <h1 style="text-align: end">작성일: {{ qna.date }}</h1>
                  <h1 style="text-align: end">작성자: {{ qna.author }} 조회수: {{ qna.viewCount }}</h1>
                </v-col>
              </v-row>
            </v-card-subtitle>
            <v-card-text>
              <v-row>
                <v-textarea v-html="message" disabled rows="10" max-rows="30" class="article-content ml-2 mb-4 pb-4"> </v-textarea>
              </v-row>
            </v-card-text>
          </div>
        </v-card>

        <div v-if="qna.comments[0].date">
          <v-card class="mt-2" v-for="(comment, index) in qna.comments" :key="index" outlined>
            <div class="ml-3">
              <v-card-subtitle>
                <v-row>
                  <v-col class="pl-1" cols="2"> </v-col>
                  <v-col cols="2"></v-col>
                  <v-col cols="8" class="">
                    <h1 style="text-align: end">작성일: {{ comment.date }}</h1>
                    <h1 style="text-align: end">작성자: {{ comment.author }}</h1>
                  </v-col>
                </v-row>
              </v-card-subtitle>
              <v-card-text>
                <v-row>
                  <v-textarea v-html="comment.content" disabled rows="10" max-rows="30" class="article-content ml-2 mb-4 pb-4"> </v-textarea>
                </v-row>
              </v-card-text>
            </div>
          </v-card>
        </div>
      </v-col>
    </v-row>

    <qna-comment-item :no="this.no"></qna-comment-item>

    <v-row class="ml-0 mr-0">
      <v-col class="text-left">
        <v-btn depressed @click="moveList">목록</v-btn>
      </v-col>
      <v-col class="text-right">
        <v-btn class="mr-4" color="primary" depressed @click="moveModify" v-if="isMyArticle()">수정</v-btn>
        <v-btn color="error" depressed @click="deleteQna" v-if="isMyArticle()">삭제</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { apiQna } from "@/api/index";
import { mapGetters } from "vuex";
import QnaCommentItem from "@/components/qna/item/QnaCommentItem.vue";
const apiQnaFunc = apiQna();

export default {
  components: {
    QnaCommentItem,
  },
  name: "QnaDetail",
  props: ["no"],
  data() {
    return {
      qna: {
        comments: [
          {
            date: null,
          },
        ],
      },
    };
  },
  computed: {
    ...mapGetters("userStore", ["userInfo", "isLogin"]),
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
      apiQnaFunc.get(`/${this.no}`).then(({ data }) => {
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
        apiQnaFunc.delete(`/${this.qna.no}`).then(({ status }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (status == 200) {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
      }
    },
    isMyArticle() {
      if (this.userInfo && this.userInfo.username) {
        return this.qna.author === this.userInfo.username || this.userInfo.userid === "admin";
      } else return false;
    },
  },
};
</script>

<style>
.detail-container {
  max-height: 80vh;
  overflow: scroll;
  overflow-x: hidden;
}


.article-content {
  color: #2a2a2a !important;
}
</style>
