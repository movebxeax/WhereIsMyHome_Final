<template>
  <div class="ma-10">
    <!-- <v-card class="ma-9" elevation="10" color="transparent" outlined> -->
    <v-container>
      <v-row style="height:15vh">
        <v-col class="pa-auto ma-auto" cols="12" sm="6" md="4">
          Happy House Logo Here
        </v-col>
      </v-row>
      <v-row class="d-flex flex-wrap" style="height: 15vh">
        <v-col class="pa-auto ma-auto" cols="12" sm="8" md="4">
          <v-text-field class="text-field-class" color="grey lighten-43" placeholder="가격이 궁금한 지역을 입력해보세요"
            append-icon="mdi-magnify" solo rounded large></v-text-field>
        </v-col>
      </v-row>
    </v-container>
    <!-- </v-card> -->
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.text-field-class>>>input {
  text-align: center;
  font-size: 20px;
}

.v-input {
  width: 80vw;
}
</style>
