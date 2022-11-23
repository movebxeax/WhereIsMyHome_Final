<template>
  <v-container>
    <div class="text-lg-center pa-5" style="width: 100%" v-if="chartLoading">
      <v-progress-circular width="7" size="70" indeterminate color="red"></v-progress-circular>
    </div>
    <div v-else>
      <apexchart type="scatter" :options="chartOptions" :series="priceData"></apexchart>
    </div>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import VueApexCharts from "vue-apexcharts";
import { priceFormatter } from "@/utils/etc";
const tradeStore = "tradeStore";

export default {
  name: "TradeSideBarGraph",
  components: {
    apexchart: VueApexCharts,
  },
  computed: {
    ...mapGetters(tradeStore, ["apt"]),
    priceData() {
      return this.priceInfoList.map((priceInfo) => ({
        name: `${priceInfo.dealYear}년 ${priceInfo.dealMonth}월`,
        data: [[new Date(priceInfo.dealYear + "-" + priceInfo.dealMonth).getTime(), priceInfo.price]],
      }));
    },
  },
  props: {
    priceInfoList: {
      type: Array,
    },
  },
  data() {
    return {
      chartLoading: true,
      chartOptions: {
        chart: {
          type: "scatter",
          zoom: {
            type: "xy",
          },
        },
        dataLabels: {
          enabled: false,
        },
        grid: {
          xaxis: {
            lines: {
              show: true,
            },
          },
          yaxis: {
            lines: {
              show: true,
            },
          },
        },
        xaxis: {
          type: "datetime",
          labels: {
            format: "yyyy.MM",
          },
        },
        yaxis: {
          min: 0,
          max: 0,
          tickAmount: 6,
          labels: {
            minWidth: 45,
            style: {
              fontSize: "12px",
              fontWeight: 400,
            },
            formatter: (value) => {
              return priceFormatter(value);
            },
          },
        },
        legend: {
          show: false,
        },
        tooltip: {
          x: {
            show: false,

            format: "yyyy.MM",
          },
        },
      },
    };
  },
  mounted() {
    this.chartLoading = true;
    this.chartOptions.yaxis.min = Math.min(...this.priceData.map((detail) => detail.data[0][1]).map(Number)) - 10000;
    this.chartOptions.yaxis.max = Math.max(...this.priceData.map((detail) => detail.data[0][1]).map(Number)) + 10000;
    this.chartLoading = false;
  },
};
</script>

<style></style>
