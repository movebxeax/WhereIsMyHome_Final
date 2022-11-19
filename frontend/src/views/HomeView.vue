<template>
  <div style="z-index: 0">
    <div class="col-md-5 p-lg-5 mx-auto my-5 back">
      <video class="bg-video" playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
        <source src="../assets/img/bg.mp4" type="video/mp4" />
      </video>
    </div>
    <div>
      <v-row class="pa-5 ma-10" align="center" justify="center" dense>
        <v-col>
          <v-card>
            <v-card-text>test</v-card-text>
          </v-card>
        </v-col>
        <v-col>
          <v-card>
            <v-card-text>test</v-card-text>
          </v-card>
        </v-col>
        <v-col>
          <v-card>
            <v-card-text>test</v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </div>
    <h1>아파트 거래 조회</h1>
    <h1>카드 섹션</h1>
  </div>
</template>

<script>
export default {
  name: "HomeView",
};
</script>

<style scoped>
/* @media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
} */

video.bg-video {
  justify-content: center;
  width: 100vw;
  background-size: cover;
  transition: 1s opacity;
  opacity: 0.3;
  position: absolute;
  clip-path: polygon(0 0, 100vw 0, 100vw 80vh, 0 80vh);
  overflow: hidden;
  z-index: 0;
  top: 0;
  left: 0;
}
</style>
