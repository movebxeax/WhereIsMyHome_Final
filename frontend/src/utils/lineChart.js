import { Line } from "vue-chartjs";
import { Chart, registerables } from "chart.js";

Chart.register(...registerables);

export default {
  extends: Line,
  props: ["chartData", "options"],
  created() {
    this.renderChart(this.chartData, this.options);
  },
};
