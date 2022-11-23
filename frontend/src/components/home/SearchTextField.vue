<template>
  <div class="ma-10">
    <!-- <v-card class="ma-9" elevation="10" color="transparent" outlined> -->
    <v-container fill-height>
      <v-row style="height: 15vh">
        <v-col class="pa-auto ma-auto" cols="12" style="font-weight: bolder; font-size: 2rem;"> 당신이 원하는 집은
          어떤 곳인가요?
        </v-col>
      </v-row>
      <v-row style="height: 15vh" justify="center">
        <v-col class="pa-0 ma-0" cols="4">
          <v-autocomplete class="auto-complete-class text-field-class" color="grey lighten-43" :value="inputDongcode"
            @input="setInputDongcode" :items="searchOptions" :loading="isLoading" :search-input.sync="searchKeyword"
            clearable hide-details hide-selected item-text="address" item-value="dongcode" label="가격이 궁금한 지역을 입력해보세요."
            solo rounded>
            <template v-slot:no-data>
              <v-list-item>
                <v-list-item-title>
                  검색어를 입력하세요.
                  <strong> (역삼동, 종로구)</strong>
                </v-list-item-title>
              </v-list-item>
            </template>
            <template v-slot:selection="{ attr, on, item, selected }">
              <v-chip v-bind="attr" :input-value="selected" color="blue-grey" class="white--text" v-on="on" justif>
                <span v-text="item.address"></span>
              </v-chip>
            </template>
            <template v-slot:item="{ item }">
              <v-list-item-content>
                <v-list-item-title v-text="item.address"></v-list-item-title>
              </v-list-item-content>
            </template>
          </v-autocomplete>
        </v-col>
        <v-col cols="auto" class="pa-1 ma-1">
          <v-btn rounded @click="moveTrade">
            <v-icon color="primary">mdi-magnify</v-icon>
          </v-btn>
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
    ...mapActions(tradeStore, ["getSearchOptionList", "setInputDongcode", "clearApt", "clearInputDongcode"]),
    moveTrade() {
      if (!this.inputDongcode) {
        alert("검색어를 입력하세요.");
        return;
      }
      this.$router.push("/trade");
    },
  },
  created() {
    this.clearInputDongcode();
  },
};
</script>

<style scoped>
.text-field-class>>>input {
  font-size: 20px;
}

.v-input {
  width: 80vw;
}

.auto-complete-class {
  width: 100vw !important;
}
</style>
