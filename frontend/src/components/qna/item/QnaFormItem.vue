<template>
  <v-row>
    <v-col style="text-align: left">
      <form>
        <v-text-field id="userid" :disabled="isUserid" label="작성자" required></v-text-field>

        <v-text-field id="title" v-model="qna.title" ref="title" label="제목" placeholder="제목을 입력하세요."></v-text-field>

        <v-textarea id="content" v-model="qna.content" ref="content" label="내용" placeholder="내용을 입력하세요." rows="10"
          max-rows="15"></v-textarea>

        <v-row>
          <v-col class="text-left">
            <v-btn depressed @click="moveList">목록</v-btn>
          </v-col>
          <v-col class="text-right">
            <v-btn class="mr-4" type="submit" color="primary" depressed @click="onSubmit"
              v-if="this.type === 'register'">작성</v-btn>
            <v-btn class="mr-4" type="submit" color="primary" depressed @click="onSubmit" v-else>수정</v-btn>
            <v-btn class="mr-4" type="reset" color="error" depressed @click="onReset">초기화</v-btn>
          </v-col>
        </v-row>
      </form>
    </v-col>
  </v-row>
</template>

<script>
import { apiQna } from "@/api/index";
const apiQnaFunc = apiQna();
export default {
  name: "QnaFormItem",
  props: {
    no: {
      type: String,
    },
    type: {
      type: String,
    },
  },
  data() {
    return {
      qna: {
        no: 0,
        title: "",
        content: "",
        author: "",
      },
      isUserid: false,
    };
  },
  created() {
    if (this.type === "modify") {
      this.isUserid = true;
      apiQnaFunc.get(`/${this.no}`).then(({ data }) => {
        this.qna = data;
      });
    }
  },
  methods: {
    onSubmit(event) {
      console.log(event);
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.qna.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.qna.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());
      if (!err) alert(msg);
      else this.type === "register" ? this.registQna() : this.modifyQna();
    },
    onReset(event) {
      console.log(event);
      event.preventDefault();
      this.qna.title = "";
      this.qna.content = "";
    },
    registQna() {
      console.log("register action");
      const body = {
        title: this.qna.title,
        content: this.qna.content,
        author: "testuser",
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
  },
};
</script>

<style>

</style>
