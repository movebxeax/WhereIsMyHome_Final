<template>
  <v-container class="list-container">
    <v-row class="notice-item">
      <v-col>
        <v-data-table class="ma-3" :items="notices" :headers="headers" cursor="pointer" @click:row="viewNotice">
        </v-data-table>
      </v-col>
    </v-row>

    <v-row class="justify-end ma-3">
      <v-spacer></v-spacer>
      <v-col cols="2">
        <v-select v-model="noticeSelectedType" :items="noticeSearchKeywords" item-text="text" item-value="value"
          persistent-hint return-object style="text-align:center"></v-select>
      </v-col>
      <v-col cols="3">
        <v-text-field v-model="noticeSearchInput" ref="search" id="notice-search" label="검색어" append-icon="mdi-magnify"
          @click:append="onSearch">
        </v-text-field>
      </v-col>
      <v-col class="d-flex align-center justify-row-reverse">
        <v-spacer></v-spacer>
        <v-btn color="primary" @click="moveRegister" v-if="isLogin">글쓰기</v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { apiNotice } from "@/api/index";
import { mapGetters } from "vuex";

const apiNoticeFunc = apiNotice();

export default {
  name: "NoticeList",
  computed: {
    ...mapGetters("userStore", ["isLogin"]),
  },
  data() {
    return {
      notices: [],
      headers: [
        { value: "no", text: "글번호", width: "10%" },
        { value: "title", text: "제목", width: "50%" },
        { value: "author", text: "작성자", width: "15%" },
        { value: "date", text: "작성일", width: "15%" },
        { value: "viewCount", text: "조회수", width: "10%" },
      ],
      noticeSearchKeywords: [
        { value: null, text: "검색" },
        { value: "title", text: "제목" },
        { value: "author", text: "작성자" },
      ],
      noticeSearchInput: "",
      noticeSelectedType: { value: null, text: "검색" },
    };
  },
  created() {
    this.getNotices();
  },
  methods: {
    getNotices(params) {
      apiNoticeFunc.get("", { params: params }).then(({ data }) => {
        console.log(data);
        this.notices = data;
      });
    },
    moveRegister() {
      this.$router.push("/notice/register");
    },
    viewNotice(notice) {
      this.$router.push(`/notice/detail/${notice.no}`);
    },
    onSearch(event) {
      console.log(event);
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.noticeSearchInput && ((msg = "검색어를 입력해주세요"), (err = false), this.$refs.search.focus());
      if (!err) alert(msg);
      else {
        let params;
        if (this.noticeSelectedType.value === "title") {
          params = {
            title: this.noticeSearchInput,
          };
        } else {
          params = {
            author: this.noticeSearchInput,
          };
        }
        this.getNotices(params);
      }
    },
  },
};
</script>

<style scoped>
.notice-item {
  cursor: pointer;
}

.v-data-table>>>thead>tr>th {
  font-size: 1.1rem !important;
  vertical-align: middle;
  text-align: center !important;
  justify-content: space-between;
}

.v-data-table>>>tbody>tr>td {
  font-size: 0.9rem !important;
  vertical-align: middle;
  text-align: center !important;
}

.v-select__selection {
  justify-content: center;
}

.list-container {
  max-height: 80vh;
}

.notice-item {
  max-height: 70vh;
  overflow: scroll;
  overflow-x: hidden
}

.notice-item::-webkit-scrollbar {
  width: 3px;
  height: 8px;
  /*background-color: #aaa*/
}

.notice-item::-webkit-scrollbar-thumb {
  background: #aaa
}
</style>
