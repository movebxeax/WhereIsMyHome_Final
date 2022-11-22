<template>
  <v-row>
    <v-col style="text-align: left">
      <form>
        <v-text-field id="userid" v-model="userInfo.username" :disabled="true" label="작성자"></v-text-field>

        <v-text-field id="title" v-model="notice.title" ref="title" label="제목" placeholder="제목을 입력하세요."></v-text-field>

        <v-textarea id="content" v-model="notice.content" ref="content" label="내용" placeholder="내용을 입력하세요." rows="10" max-rows="15"></v-textarea>

        <v-row>
          <v-col class="text-left">
            <v-btn depressed @click="moveList">목록</v-btn>
          </v-col>
          <v-col class="text-right">
            <v-btn class="mr-4" type="submit" color="primary" depressed @click="onSubmit" v-if="this.type === 'register'">작성</v-btn>
            <v-btn class="mr-4" type="submit" color="primary" depressed @click="onSubmit" v-else>수정</v-btn>
            <v-btn class="mr-4" type="reset" color="error" depressed @click="onReset">초기화</v-btn>
          </v-col>
        </v-row>
      </form>
    </v-col>
  </v-row>
</template>

<script>
import { apiNotice } from "@/api/index";
import { mapGetters } from "vuex";

const apiNoticeFunc = apiNotice();

export default {
  name: "NoticeFormItem",
  props: {
    no: {
      type: String,
    },
    type: {
      type: String,
    },
  },
  computed: {
    ...mapGetters("userStore", ["userInfo"]),
  },
  data() {
    return {
      notice: {
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
      apiNoticeFunc.get(`/${this.no}`).then(({ data }) => {
        this.notice = data;
      });

      if (this.userInfo.userid !== this.notice.author) {
        alert("잘못된 접근입니다.");
        this.$router.push("/notice/list");
      }
    }
  },
  methods: {
    onSubmit(event) {
      console.log(event);
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.notice.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.notice.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());
      if (!err) alert(msg);
      else this.type === "register" ? this.registNotice() : this.modifyNotice();
    },
    onReset(event) {
      console.log(event);
      event.preventDefault();
      this.notice.title = "";
      this.notice.content = "";
    },
    registNotice() {
      console.log("register action");
      const body = {
        title: this.notice.title,
        content: this.notice.content,
        author: this.userInfo.userid,
      };

      apiNoticeFunc.post("", body).then(({ status }) => {
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (status == 200) {
          msg = "등록이 완료되었습니다.";
        }
        alert(msg);
        this.moveList();
      });
    },
    modifyNotice() {
      console.log("modify action");
      const body = {
        title: this.notice.title,
        content: this.notice.content,
        author: "testuser",
      };

      apiNoticeFunc.post(`/${this.notice.no}`, body).then(({ status, data }) => {
        console.log(data.no);
        let msg = "수정 처리시 문제가 발생했습니다.";
        if (status == 200) {
          msg = "수정이 완료되었습니다.";
        }
        alert(msg);
        this.viewNotice(data.no);
      });
    },
    moveList() {
      this.$router.push("/notice");
    },
    viewNotice(noticeno) {
      this.$router.push(`/notice/detail/${noticeno}`);
    },
  },
};
</script>

<style></style>
