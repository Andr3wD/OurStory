<script>
import VueCharts, { Line } from 'vue-chartjs'

export default {
  extends: Line,
  props: [],
  mixins: [VueCharts.mixins.reactiveProp],
  mounted () {
    this.renderChart(this.chartData)
  }

}
</script>
