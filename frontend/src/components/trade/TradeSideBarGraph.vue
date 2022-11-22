<template>
  <v-container>
    <div class="text-lg-center pa-5" style="width: 100%" v-if="chartLoading">
      <v-progress-circular width="7" size="70" indeterminate color="red"></v-progress-circular>
    </div>
    <Scatter :chart-data="chartData" id="chart" v-else></Scatter>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import { Scatter } from "vue-chartjs";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
  PointElement,
} from "chart.js";

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, PointElement);

const tradeStore = "tradeStore";

export default {
  name: "TradeSideBarGraph",
  components: {
    Scatter,
  },
  computed: {
    ...mapGetters(tradeStore, ["apt"]),
    priceData() {
      return this.priceInfoList.map((priceInfo) => ({
        x: new Date(priceInfo.dealYear + "-" + priceInfo.dealMonth) / 1000,
        y: priceInfo.price,
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
      chartData: {
        // labels: [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023],
        datasets: [
          {
            label: "거래",
            backgroundColor: "rgb(255,153,204, 0.5)",
            pointBackgroundColor: "skyblue",
            borderColor: "skyblue",
            pointBorderColor: "skyblue",
            data: [],
          },
        ],
      },
      options: {
        scales: {
          x: {
            type: "time",
            time: {
              unit: "year",
            },
          },
        },
      },
    };
  },
  mounted() {
    this.chartLoading = true;
    this.chartData.datasets[0].data = this.priceData;
    this.chartLoading = false;
  },
  methods: {},
};
</script>

<style></style>
