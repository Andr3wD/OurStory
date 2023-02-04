<template>
  <b-container class="d-flex justify-content-center">
    <APIRequestChart :chart-data="this.chartData" />

  </b-container>
</template>

<script>

import AdminService from '../services/admin-service'
import APIRequestChart from '../components/APIRequestChart.vue'

export default {
  name: 'Admin',
  components: {
    APIRequestChart
  },
  data () {
    return {
      userStats: null,
      chartData: null
    }
  },
  methods: {
  },
  created () {
    AdminService.getUserStats()
      .then(
        response => {
          this.userStats = response.data
          var tempLabels = []
          for (let index = 0; index < 24; index++) {
            tempLabels.push(index)
          }
          var tempData = []
          for (let index = 0; index < 24; index++) {
            tempData.push(0)
          }
          var hour = 0
          this.userStats.forEach(v => {
            hour = v.date.hourOfDay
            tempData[hour] += 1
          })
          this.chartData = {
            labels: tempLabels,
            datasets: [{
              label: 'API Requests',
              data: tempData
            }]
          }
        }
      ).catch(e => {
        console.log(e)
      })
  }
}

</script>

<style>

</style>
