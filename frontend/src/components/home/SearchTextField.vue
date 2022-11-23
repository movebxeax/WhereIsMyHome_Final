<template>
  <div class="ma-10">
    <!-- <v-card class="ma-9" elevation="10" color="transparent" outlined> -->
    <v-container>
      <v-row style="height: 15vh">
        <v-col class="pa-auto ma-auto" cols="12" sm="6" md="4"> Happy House Logo Here </v-col>
      </v-row>
      <v-row class="d-flex flex-wrap" style="height: 15vh">
        <v-col class="pa-auto ma-auto" cols="12" sm="8" md="4">
          <!-- <v-text-field class="text-field-class" color="grey lighten-43" placeholder="가격이 궁금한 지역을 입력해보세요"
            append-icon="mdi-magnify" solo rounded large></v-text-field> -->
          <v-autocomplete
            class="text-field-class"
            color="grey lighten-43"
            :value="inputDongcode"
            @input="setInputDongcode"
            :items="searchOptions"
            :loading="isLoading"
            :search-input.sync="searchKeyword"
            clearable
            hide-details
            hide-selected
            item-text="address"
            item-value="dongcode"
            label="가격이 궁금한 지역을 입력해보세요."
            solo
            rounded
            large
          >
            <template v-slot:no-data>
              <v-list-item>
                <v-list-item-title>
                  검색어를 입력하세요.
                  <strong> (역삼동, 종로구)</strong>
                </v-list-item-title>
              </v-list-item>
            </template>
            <template v-slot:selection="{ attr, on, item, selected }">
              <v-chip v-bind="attr" :input-value="selected" color="blue-grey" class="white--text" v-on="on">
                <span v-text="item.address"></span>
              </v-chip>
            </template>
            <template v-slot:item="{ item }">
              <v-list-item-content>
                <v-list-item-title v-text="item.address"></v-list-item-title>
              </v-list-item-content>
            </template>
            <template v-slot:append>
              <v-slide-x-reverse-transition mode="out-in">
                <v-icon color="primary" @click="moveTrade">mdi-magnify</v-icon>
              </v-slide-x-reverse-transition>
            </template>
          </v-autocomplete>
        </v-col>
      </v-row>
    </v-container>
    <!-- </v-card> -->
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

const tradeStore = "tradeStore";

export default {
  data() {
    return {
      searchKeyword: "",
      isLoading: false,
    };
  },
  computed: {
    ...mapGetters(tradeStore, ["searchOptions", "inputDongcode"]),
  },
  watch: {
    searchKeyword() {
      if (this.searchOptions.length > 0) return;

      this.isLoading = true;

      this.getSearchOptionList(this.searchKeyword).finally(() => (this.isLoading = false));
    },
  },
  methods: {
    ...mapActions(tradeStore, ["getSearchOptionList", "setInputDongcode"]),
    moveTrade() {
      console.log(this.inputDongcode);
      if (!this.inputDongcode) {
        alert("검색어를 입력하세요.");
        return;
      }
      this.$router.push("/trade");
    },
  },
};
</script>

<style scoped>
.text-field-class >>> input {
  text-align: center;
  font-size: 20px;
}

.v-input {
  width: 80vw;
}
</style>
