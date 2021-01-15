<template>
  <div>
    <div class="storyArea">
      <a>TEXT</a>
    </div>
    <div class="input-group mb-3">

    <input @keyup="updateWordCount()" v-model="segment" type="text" class="form-control" placeholder="Text">
      <div class="input-group-append">
        <span class="input-group-text"> > </span>
      </div>
    </div>
    <span>Characters Left: {{charsLeft}}</span>
    <span>First Segment: {{this.firstSegment}} </span>
  </div>
</template>

<script>

import segmentService from '../services/segment-service'

export default {
  name: 'Global',
  data () {
    return {
      segment: '',
      maxChars: 100,
      charsLeft: 100,
      firstSegment: ''
    }
  },
  methods: {
    updateWordCount () {
      this.charsLeft = this.maxChars - this.segment.length
    },
    getFirstSegment () {
      segmentService.getFirstSegment()
        .then(
          response => {
            this.firstSegment = response.data
          }
        ).catch(e => {

        })
    }
  },
  mounted () {
    this.getFirstSegment()
  }
}
</script>

<style>
.storyArea {
  height: 100%;
}

</style>
