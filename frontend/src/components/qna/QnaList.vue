<template>
  <v-container>
    <v-row>
      <v-col>
        <v-data-table
          class="qna-item"
          :items="qnas"
          :headers="headers"
          cursor="pointer"
          @click:row="viewQna"
        ></v-data-table>
      </v-col>
    </v-row>

    <v-row align="center" justify="space-around">
      <v-spacer></v-spacer>
      <v-col cols="2">
        <v-select
          v-model="qnaSelectedType"
          :items="qnaSearchKeywords"
          item-text="text"
          item-value="value"
          persistent-hint
          return-object
        ></v-select>
      </v-col>
      <v-col cols="3">
        <v-text-field v-model="qnaSearchInput" ref="search" id="qna-search" label="검색어"></v-text-field>
      </v-col>
      <v-btn icon color="primary" @click="onSearch">
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn color="primary" @click="moveRegister">글쓰기</v-btn>
    </v-row>
  </v-container>
</template>

<script>
import { httpQna } from "@/utils/api";

export default {
  name: "QnaList",
  data() {
    return {
      qnas: [],
      headers: [
        { value: "no", text: "글번호", width: "10%" },
        { value: "title", text: "제목", width: "50%" },
        { value: "author", text: "작성자", width: "15%" },
        { value: "date", text: "작성일", width: "15%" },
        { value: "viewCount", text: "조회수", width: "10%" },
      ],
      qnaSearchKeywords: [
        { value: null, text: "검색" },
        { value: "title", text: "제목" },
        { value: "author", text: "작성자" },
      ],
      qnaSearchInput: "",
      qnaSelectedType: { value: null, text: "검색" },
    };
  },
  created() {
    this.getQnas();
  },
  methods: {
    getQnas(params) {
      httpQna.get("", { params: params }).then(({ data }) => {
        this.qnas = data;
      });
    },
    moveRegister() {
      this.$router.push("/qna/register");
    },
    viewQna(qna) {
      this.$router.push(`/qna/detail/${qna.no}`);
    },
    onSearch(event) {
      console.log(event);
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.qnaSearchInput && ((msg = "검색어를 입력해주세요"), (err = false), this.$refs.search.focus());
      if (!err) alert(msg);
      else {
        let params;
        if (this.qnaSelectedType.value === "title") {
          params = {
            title: this.qnaSearchInput,
          };
        } else {
          params = {
            author: this.qnaSearchInput,
          };
        }
        this.getQnas(params);
      }
    },
  },
};
</script>

<style scoped>
.qna-item {
  cursor: pointer;
}
</style>
