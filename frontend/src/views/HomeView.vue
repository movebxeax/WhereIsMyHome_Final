<template>
  <v-main>
    <div style="z-index: 0">
      <backgroud-video></backgroud-video>
      <search-text-field></search-text-field>
      <card-sections></card-sections>
    </div>
  </v-main>
</template>

<script>
import BackgroudVideo from "@/components/home/Background.vue";
import CardSections from "@/components/home/CardSections.vue";
import SearchTextField from "@/components/home/SearchTextField.vue";
export default {
  name: "HomeView",
  components: { BackgroudVideo, SearchTextField, CardSections },
};
</script>

<style ,
    SearchTextFieldscoped>
    
    </style>
