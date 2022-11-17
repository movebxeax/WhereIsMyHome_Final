<template>
  <v-container>
    <v-row>
      <v-col>
        <h3>Q&A 수정</h3>
      </v-col>
    </v-row>
    <qna-form-item type="modify" :no="this.no"></qna-form-item>
  </v-container>
</template>

<script>
import QnaFormItem from "@/components/qna/item/QnaFormItem.vue";

export default {
  name: "QnaForm",
  components: { QnaFormItem },
  props: {
    no: {
      type: String,
    },
  },
};
</script>

<style></style>
