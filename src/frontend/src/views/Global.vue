<template>
  <div>
    <span class="storyArea" v-for="(s, index) in segments"
    :key="index">
      <story-text @mouseenter="hovered = index + 1" @mouseleave="hovered = 0" :class="{ active: hovered == index + 1 }">{{ s }} </story-text>
    </span>
    <div class="input-group mb-3">

    <input @keyup="updateWordCount()" v-model="segment" type="text" class="form-control" placeholder="Text">
      <div class="input-group-append">
        <span class="input-group-text"> > </span>
      </div>
    </div>
    <div>Characters Left: {{charsLeft}}</div>
    <span v-if="hovered != 0">Segment added by: (NAME) </span>
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
      realSegments: [],
      hovered: 0
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

.active {
  color: red;
}

</style>
