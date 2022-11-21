<template>
  <v-main>
    <div style="z-index: 0">
      <backgroud-video></backgroud-video>
      <div style="height: 30vh">
        <div class="align-center">
          <h1>아파트 거래 검색창이 여기 들어갑니다.</h1>
        </div>
      </div>
      <card-sections></card-sections>
    </div>
  </v-main>
</template>

<script>
import BackgroudVideo from "@/components/home/Background.vue";
import CardSections from "@/components/home/CardSections.vue";
export default {
  name: "HomeView",
  components: { BackgroudVideo, CardSections },
};
</script>

<style scoped></style>
