<template>
  <v-container>
    <v-row>
      <v-col>
        <h3>공지사항 수정</h3>
      </v-col>
    </v-row>
    <notice-form-item type="modify" :no="this.no"></notice-form-item>
  </v-container>
</template>

<script>
import NoticeFormItem from "@/components/notice/item/NoticeFormItem.vue";

export default {
  name: "NoticeForm",
  components: { NoticeFormItem },
  props: {
    no: {
      type: String,
    },
  },
};
</script>

<style></style>
