<template>
  <v-container>
    <h1>그래프 컴포넌트</h1>
    <line-chart ref="lineChart" :chartData="chart.data" :options="chart.options" id="chart"></line-chart>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import { Chart, registerables } from "chart.js";
import LineChart from "@/utils/lineChart.js";

const tradeStore = "tradeStore";
Chart.register(...registerables);

export default {
  name: "TradeSideBarGraph",
  components: {
    LineChart,
  },
  computed: {
    ...mapGetters(tradeStore, ["apt"]),
    priceData() {
      console.log(this.priceInfoList);
      return this.priceInfoList.map((priceInfo) => priceInfo.price);
    },
    yearLabelData() {
      return this.priceInfoList.map((priceInfo) => priceInfo.dealYear);
    },
  },
  props: {
    priceInfoList: {
      type: Array,
    },
  },
  data() {
    return {
      chart: {
        data: {
          //   labels: [2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023],
          labels: [],
          datasets: [
            {
              label: "가격",
              backgroundColor: "rgb(255,153,204, 0.5)",
              pointBackgroundColor: "hotpink",
              tension: 0.1,
              borderColor: "hotpink",
              pointBorderColor: "hotpink",
              borderWidth: 1,
              pointBorderWidth: 1,
              data: [],
            },
          ],
        },
        options: {
          plugins: {
            legend: {
              display: true,
              position: "left",
              labels: {
                boxWidth: 8,
                padding: 10,
                usePointStyle: true,
                pointStyle: "circle",
                font: {
                  size: 14,
                },
              },
              fullSize: true,
              align: "center",
            },
            tooltip: {
              boxWidth: 15,
              bodyFont: {
                size: 14,
              },
            },
          },
          scales: {
            x: {
              grid: {
                display: false,
              },
              ticks: {
                padding: 3,
              },
            },
            y: {
              ticks: {
                callback: (val) => {
                  return val !== 0 ? val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") : "";
                },
                padding: 10,
              },
              grid: {
                drawBorder: false,
                color: "#323232",
                lineWidth: 1,
              },
            },
          },
          responsive: true,
          maintainAspectRatio: false,
          animation: {
            duration: 5000,
          },
        },
      },
    };
  },
  created() {
    this.chart.data.datasets[0].data = this.priceData;
    this.chart.data.labels = this.yearLabelData;
  },
};
</script>

<style></style>
