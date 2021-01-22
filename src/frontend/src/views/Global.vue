<template>
  <div>
    <div class="storyArea" v-for="(s, index) in realSegments"
    :key="index">
      <a>{{ s.message }}</a>
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
      segments: ['message1', 'message2', 'message3', 'message4', 'message5', 'message6'],
      segment: '',
      maxChars: 100,
      charsLeft: 100,
      firstSegment: '',
      realSegments: []
    }
  },
  methods: {
    updateWordCount () {
      this.charsLeft = this.maxChars - this.segment.length
    },
    getGlobalSegments () {
      segmentService.getSegments('global')
        .then(
          response => {
            this.realSegments = response.data
            console.log(this.realSegments)
          }
        ).catch(e => {

        })
    }
  },
  mounted () {
    this.getGlobalSegments()
  }
}
</script>

<style>
.storyArea {
  height: 100%;
}

</style>
