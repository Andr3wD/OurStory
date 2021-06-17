<template>
  <div>
    <span class="storyArea" v-for="(s, index) in realSegments"
    :key="index">
      <div @mouseenter="hovered = index" @mouseleave="hovered = -1" :class="{active: hovered == index}" class="ml-2" style="">{{ s.message }}</div>
    </span>
    <div class="input-group mb-3">

    <input @submit="submitMessage()" v-model="currentSegment" type="text" class="form-control" placeholder="Text">
      <div class="input-group-append">
        <button class="input-group-text" @click="submitMessage()"> > </button>
      </div>
    </div>
    <div>Characters Left: {{this.maxChars - this.currentSegment.length}}</div>
    <span v-if="hovered != -1">Segment added by: {{realSegments[hovered].username}} at {{realSegments[hovered].date}}</span>
  </div>
</template>

<script>

import segmentService from '../services/segment-service'

export default {
  name: 'Global',
  data () {
    return {
      currentSegment: '',
      maxChars: 100,
      charsLeft: 100,
      realSegments: [],
      hovered: -1
    }
  },
  methods: {
    submitMessage (e) {
      console.log('submitting message')
      // Submit the segment to the backend, with story title 'global'
      segmentService.submitSegment(this.currentSegment, 'global').then(
        response => {
          console.log(response)
          if (response.status === 200) { // OK
            // Update the whole story.
            this.getGlobalSegments()
          }
        }
      )
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
  display: inline-block;
}

.active {
  color: red;
}

</style>
