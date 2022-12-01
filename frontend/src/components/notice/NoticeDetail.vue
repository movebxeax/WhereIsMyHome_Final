<template>
  <v-container class="detail-container">
    <v-row class="mb-1">
      <v-col>
        <v-card outlined>
          <div>
            <v-card-title class="d-flex justify-content ml-1">
              {{ notice.title }}
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
                  <h1 style="text-align: end">작성일: {{ notice.date }}</h1>
                  <h1 style="text-align: end">작성자: {{ notice.author }} 조회수: {{ notice.viewCount }}</h1>
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
      </v-col>
    </v-row>

    <v-row class="ml-0 mr-0">
      <v-col class="text-left">
        <v-btn depressed @click="moveList">목록</v-btn>
      </v-col>
      <v-col class="text-right">
        <v-btn class="mr-4" color="primary" depressed @click="moveModify" v-if="isMyArticle()">수정</v-btn>
        <v-btn color="error" depressed @click="deleteNotice" v-if="isMyArticle()">삭제</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { apiNotice } from "@/api/index";
import { mapGetters } from "vuex";
const apiNoticeFunc = apiNotice();

export default {
  name: "NoticeDetail",
  props: ["no"],
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    ...mapGetters("userStore", ["userInfo", "isLogin"]),
    message() {
      if (this.notice.content) return this.notice.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    this.getNotice();
  },
  methods: {
    getNotice() {
      apiNoticeFunc.get(`/${this.no}`).then(({ data }) => {
        this.notice = data;
      });
    },
    moveList() {
      this.$router.push("/notice");
    },
    moveModify() {
      this.$router.push(`/notice/modify/${this.no}`);
    },
    deleteNotice() {
      if (confirm("삭제하시겠습니까?")) {
        apiNoticeFunc.delete(`/${this.notice.no}`).then(({ status }) => {
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
        return this.notice.author === this.userInfo.username || this.userInfo.userid === "admin";
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

.detail-container::-webkit-scrollbar {
  width: 3px;
  height: 8px;
  /*background-color: #aaa*/
}

.detail-container::-webkit-scrollbar-thumb {
  background: #aaa;
}

.article-content {
  color: #2a2a2a !important;
}
</style>
