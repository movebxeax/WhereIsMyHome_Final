<template>
  <div class="col-md-5 p-lg-5 mx-auto my-5 back">
    <video class="bg-video" playsinline="playsinline" autoplay="autoplay" muted="muted" loop="loop">
      <source src="../../assets/img/bg.mp4" type="video/mp4" />
    </video>
  </div>
</template>

<script>
export default {
  name: "BackgroudVideo",
};
</script>

<style scoped>
video.bg-video {
  transition: 1s opacity;
  opacity: 0.2;
  position: fixed;
  clip-path: polygon(0 0, 100vw 0, 100vw 100vh, 0 100vh);
  overflow: hidden;
  z-index: 0;
  top: 0;
  left: 0;
}
</style>
