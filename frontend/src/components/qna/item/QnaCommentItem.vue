<template>
  <v-row class="mt-0 ml-0 mr-0">
    <v-col style="text-align: left">
      <v-expansion-panels>
        <v-expansion-panel>
          <v-expansion-panel-header>댓글 작성하기</v-expansion-panel-header>
          <v-expansion-panel-content>
            <form class="ml-6 mr-6">
              <v-text-field id="userid" v-model="comment.author" ref="author" :disabled="isLoggedIn()" label="작성자"> </v-text-field>

              <v-textarea id="content" v-model="comment.content" ref="content" placeholder="댓글을 입력하세요." rows="3" max-rows="15"> </v-textarea>

              <v-row>
                <v-col class="text-right">
                  <v-btn type="submit" color="primary" depressed @click="onSubmit">댓글 작성</v-btn>
                </v-col>
              </v-row>
            </form>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-col>
  </v-row>
</template>

<script>
import { apiQna } from "@/api/index";
import { mapGetters } from "vuex";

const apiQnaFunc = apiQna();

export default {
  name: "QnaFormItem",
  props: {
    no: {
      type: String,
    },
  },
  computed: {
    ...mapGetters("userStore", ["userInfo"]),
  },
  data() {
    return {
      comment: {
        content: "",
        author: "",
      },
      isUserid: false,
    };
  },
  created() {},
  methods: {
    onSubmit(event) {
      console.log(event);
      event.preventDefault();
      let err = true;
      let msg = "";
      err && !this.comment.author && ((msg = "이름을 입력해주세요"), (err = false), this.$refs.author.focus());
      err && !this.comment.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());
      if (!err) alert(msg);
      else this.type === "register" ? this.registQna() : this.modifyQna();
    },
    registQna() {
      console.log("register action");
      const body = {
        title: this.qna.title,
        content: this.qna.content,
        author: this.userInfo.userid,
      };

      apiQnaFunc.post("", body).then(({ status }) => {
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (status == 200) {
          msg = "등록이 완료되었습니다.";
        }
        alert(msg);
        this.moveList();
      });
    },
    modifyQna() {
      console.log("modify action");
      const body = {
        title: this.qna.title,
        content: this.qna.content,
        author: "testuser",
      };

      apiQnaFunc.post(`/${this.qna.no}`, body).then(({ status, data }) => {
        console.log(data.no);
        let msg = "수정 처리시 문제가 발생했습니다.";
        if (status == 200) {
          msg = "수정이 완료되었습니다.";
        }
        alert(msg);
        this.viewQna(data.no);
      });
    },
    moveList() {
      this.$router.push("/qna");
    },
    viewQna(qnano) {
      this.$router.push(`/qna/detail/${qnano}`);
    },
    isLoggedIn() {
      if (this.userInfo && this.userInfo.username !== "") {
        this.comment.author = this.userInfo.username;
        return this.userInfo.username !== "";
      }
    },
  },
};
</script>

<style></style>
