<template>
  <div style="z-index: 0">
    <trade-map></trade-map>
  </div>
</template>

<script>
import TradeMap from "@/components/trade/TradeMap.vue";

export default {
  components: { TradeMap },
  name: "TradeView",
};
</script>

<style lang="scss" scoped></style>
