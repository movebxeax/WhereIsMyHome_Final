<template>
  <div>
    <v-card-text class="pa-2 ma-1 text-left" v-for="(newsItem, index) in newsList" :key="index">
      <div class="d-flex">
        <div>
          <v-btn text color="#3F72AF" @click="redirectTo(newsItem.link)">
            {{ trimNewsTitle(newsItem.title) }}
          </v-btn>
        </div>
        <div class="pr-4 pt-2 ml-auto">
          <span style="mt-2">{{ toLocalDateString(newsItem.pubDate) }}</span>
        </div>
      </div>
    </v-card-text>
    <v-card-text class="pa-2 ma-1 text-left" v-for="i in 5 - Object.keys(newsList).length" :key="i">
      <div class="d-flex">
        <div>
          <v-btn text disabled color="#3F72AF"> </v-btn>
        </div>
        <div class="pr-4 ml-auto"></div>
      </div>
    </v-card-text>
  </div>
</template>

<script>
//import { httpNotice } from "@/utils/api.js";
import axios from "axios";

export default {
  name: "NewsCardText",
  data() {
    return {
      newsList: [],
    };
  },
  created() {
    axios.get("http://localhost:8080/api/news").then(({ data }) => (this.newsList = data.items));
  },
  methods: {
    // loadNoticeArticle(articleno) {
    //   this.$router.push(`/notice/detail/${articleno}`);
    // },
    toLocalDateString(string) {
      Date.prototype.YYYYMMDDHHMMSS = function () {
        var yyyy = this.getFullYear().toString();
        var MM = pad(this.getMonth() + 1, 2);
        var dd = pad(this.getDate(), 2);
        var hh = pad(this.getHours(), 2);
        var mm = pad(this.getMinutes(), 2);
        var ss = pad(this.getSeconds(), 2);

        return yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss;
      };

      function pad(number, length) {
        var str = "" + number;
        while (str.length < length) {
          str = "0" + str;
        }
        return str;
      }

      let date = new Date(string);
      return date.YYYYMMDDHHMMSS();
    },
    trimNewsTitle(string) {
      function decodeHtml(html) {
        var txt = document.createElement("textarea");
        txt.innerHTML = html;
        return txt.value;
      }

      var htmlRegexG = /<(?:"[^"]*"['"]*|'[^']*'['"]*|[^'">])+>/g;

      return decodeHtml(string.replaceAll(htmlRegexG, "")).substr(0, 25) + "....";
    },
    redirectTo(link) {
      window.open(link);
    },
  },
};
</script>

<style></style>
