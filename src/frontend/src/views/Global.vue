<template>
  <div>
    <div class="input-group mb-3">

    <input @keyup="updateWordCount()" v-model="segment" type="text" class="form-control" placeholder="Text">
      <div class="input-group-prepend">
        <span class="input-group-text"> > </span>
      </div>
    </div>
    <span>Characters Left: {{charsLeft}}</span>
  </div>
</template>

<script>

export default {
  name: 'Global',
  data () {
    return {
      segment: '',
      maxChars: 100,
      charsLeft: 100
    }
  },
  methods: {
    updateWordCount () {
      this.charsLeft = this.maxChars - this.segment.length
    }
  }
}
</script>
