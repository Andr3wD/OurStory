<template>
  <div>
    <div class="mainBox">
      <div class="storyArea">
        <b-skeleton-wrapper :loading="realSegments.length == 0">

          <template #loading>
            <b-skeleton width="100%"></b-skeleton>
            <b-skeleton width="60%"></b-skeleton>
            <b-skeleton width="80%"></b-skeleton>
            <b-skeleton width="100%"></b-skeleton>
          </template>

          <span v-b-popover.hover.top="realSegments[s.id].date" :title="realSegments[s.id].username" @mouseenter="hovered = s.id" @mouseleave="hovered = -1" :class="{active: hovered == s.id}" class="storySegment" v-for="(s, index) in this.segmentArray" :key="index">
            <div class="ml-2" style="">{{ s.word }}</div>
          </span>

        </b-skeleton-wrapper>
      </div>

      <div class="input-group" style="width:60%;margin:auto">
        <b-form-textarea :state="currentSegment.length <= maxChars" @submit="submitMessage()" v-model="currentSegment" type="text" class="form-control" :placeholder="maxChars + ' Characters Maximum'" :lazy-formatter="true"/>
        <div class="input-group-append">
          <button class="input-group-text" @click="submitMessage()">
            <b-icon icon="arrow-right" aria-hidden="true"></b-icon>
          </button>
        </div>

      </div>
      <!-- Progress bar for characters left -->
      <b-progress show-value class="charCountBar" :value="this.currentSegment.length" :max="maxChars"></b-progress>
      <!-- Text for characters left -->
      <div>Characters Left: {{this.maxChars - this.currentSegment.length}}</div>
      <!-- <span v-if="hovered != -1">Segment added by: {{realSegments[hovered].username}} at {{realSegments[hovered].date}}</span> -->
    </div>
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
      hovered: -1,
      segmentArray: null
    }
  },
  methods: {
    submitMessage (e) {
      console.log('submitting message')
      // Submit the segment to the backend, with story title 'global'
      segmentService.submitSegment(this.currentSegment, 'front-page').then(
        response => {
          console.log(response)
          if (response.status === 200) { // OK
            // Update the whole story.
            this.getGlobalSegments()
          }
        }
      )
    },
    messageFormatter (val) {
      // TODO Handle formatting the message here
    },
    generateSegmentArray () {
      var newArr = []
      var i = 0
      for (const s of this.realSegments) {
        for (const word of s.message.split(' ')) {
          newArr.push({ word: word, id: i })
        }
        i++
      }
      console.log(newArr)
      return newArr
    },
    getGlobalSegments () {
      segmentService.getSegments('front-page')
        .then(
          response => {
            this.realSegments = response.data
            console.log(this.realSegments)
            this.segmentArray = this.generateSegmentArray()
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

.charCountBar {
  width: 39%;
  margin: auto;
  margin-top: 5px;
}

.storySegment {
  display: inline-block;
}

.storyArea {
  height: 100%;
  display: inline-block;
  margin-top: 30px;
  margin-bottom: 30px;
  font-size: 1.2em;
  width: 60%;
}

.mainBox {
  width: 100%;
  text-align: center;
  margin: auto;
}

.active {
  color: red;
}

</style>
