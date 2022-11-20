<template>
  <div>
    <v-row class="pa-5 ma-10" align="center" justify="center" dense>
      <v-col class="ma-3">
        <v-card elevation="10" outlined>
          <v-card-title class="pb-0 mb-0">
            <div class="pl-3">Notice</div>
            <v-card-actions class="ml-auto">
              <v-btn text color="#3F72AF" @click="routeTo('/notice/list')">
                <v-icon>mdi-plus-box</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card-title>
          <div class="pl-2 pr-4">
            <notice-card-text class="pt-0 pb-4"></notice-card-text>
          </div>
        </v-card>
      </v-col>
      <v-col class="ma-3">
        <v-card elevation="10" outlined>
          <v-card-title class="pb-0 mb-0">
            <div class="pl-3">QnA</div>
            <v-card-actions class="ml-auto">
              <v-btn text color="#3F72AF" @click="routeTo('/qna/list')">
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
          <v-card-title class="pb-0 mb-0">
            <div class="pl-3">부동산 뉴스</div>
            <v-card-actions class="ml-auto">
              <v-btn text color="#3F72AF">
                <v-icon>mdi-plus-box</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card-title>
          <div class="pl-2 pr-4">
            <news-card-text class="pt-0 pb-4"></news-card-text>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import QnaCardText from "@/components/home/QnaCardText.vue";
import NoticeCardText from "@/components/home/NoticeCardText.vue";
import NewsCardText from "@/components/home/NewsCardText.vue";
export default {
  name: "CardSections",
  components: {
    QnaCardText,
    NoticeCardText,
    NewsCardText,
  },
  methods: {
    routeTo(uri) {
      this.$router.push(uri);
    },
  },
};
</script>

<style></style>
