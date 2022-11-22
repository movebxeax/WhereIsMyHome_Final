<template>
  <v-container>
    <v-row>
      <v-col>
        <h3>공지사항 등록</h3>
      </v-col>
    </v-row>
    <notice-form-item type="register"></notice-form-item>
  </v-container>
</template>

<script>
import NoticeFormItem from "@/components/notice/item/NoticeFormItem.vue";

export default {
  name: "noticeForm",
  components: { NoticeFormItem },
};
</script>

<style></style>
