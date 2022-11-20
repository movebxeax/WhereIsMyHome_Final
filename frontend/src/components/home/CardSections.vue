<template>
  <div>
    <v-row class="pa-5 ma-10" align="center" justify="center" dense>
      <v-col class="ma-3">
        <v-card elevation="10" outlined>
          <v-card-title class="d-flex">
            공지사항
            <v-card-actions class="ml-auto">
              <v-btn text color="#3F72AF">
                <v-icon>mdi-plus-box</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card-title>
          <v-card-text>test</v-card-text>
        </v-card>
      </v-col>
      <v-col class="ma-3">
        <v-card elevation="10" outlined>
          <v-card-title class="pb-0 mb-0">
            <div class="pl-3">QnA</div>
            <v-card-actions class="ml-auto">
              <v-btn text color="#3F72AF">
                <v-icon>mdi-plus-box</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card-title>
          <div class="pl-2 pr-4">
            <qna-card-text class="pt-0 pb-4"></qna-card-text>
          </div>
        </v-card>
      </v-col>
      <v-col class="ma-3">
        <v-card elevation="10" outlined>
          <v-card-title>
            부동산 뉴스
            <v-card-actions class="ml-auto">
              <v-btn text color="#3F72AF">
                <v-icon>mdi-plus-box</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card-title>
          <v-card-text>test</v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import QnaCardText from "@/components/home/QnaCardText.vue";
export default {
  name: "CardSections",
  components: {
    QnaCardText,
  },
};
</script>

<style></style>
