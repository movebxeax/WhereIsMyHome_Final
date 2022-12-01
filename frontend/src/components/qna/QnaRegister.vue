<template>
  <v-container>
    <v-row>
      <v-col>
        <h3>Q&A 등록</h3>
      </v-col>
    </v-row>
    <qna-form-item type="register"></qna-form-item>
  </v-container>
</template>

<script>
import QnaFormItem from "@/components/qna/item/QnaFormItem.vue";

export default {
  name: "QnaForm",
  components: { QnaFormItem },
};
</script>

<style>

</style>
