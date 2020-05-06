<template>
  <div id="tagWrapper">
    <span class="tag" v-for="(tag, idx) in tags" :key="`tag-${idx}`">
      {{ tag }} <button class="button" id="removeTagButton" @click="removeTag(idx)">&times;</button>
    </span>

    <span v-if="tagInputShown" class="tag-input">
      <input type="text" id="inputTag" v-model="currentTag">
      <button class="button" id="saveTagButton" @click="addCurrentTag">&plus;</button>
    </span>

    <button class="button" id="addTagButton" v-if="!tagInputShown" @click="showTagInput()">&plus;</button>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator'

@Component
export default class TagHolder extends Vue {
  @Prop({ default: [] }) tags!: Array<string>

  private tagInputShown = false
  private currentTag = ''

  showTagInput () {
    this.tagInputShown = true
  }

  addCurrentTag () {
    this.tags.push(this.currentTag)
    this.currentTag = ''
    this.tagInputShown = false
  }

  removeTag (id: number) {
    this.tags.splice(id, 1)
  }
}
</script>
