<template>
  <div>
    <div class="wrapper">
      <span class="tag" v-for="(tag, idx) in tags" :key="`tag-${idx}`">
        <span>{{ tag }}</span> <button class="button" id="removeTagButton" @click="removeTag(idx)">remove</button>
      </span>

      <span v-if="tagInputShown" class="tag-input">
        <input type="text" id="inputTag" v-model="currentTag">
        <button class="button" id="saveTagButton" @click="addCurrentTag">save</button>
      </span>

      <button class="button" id="addTagButton" v-if="!tagInputShown" @click="showTagInput()">add new tag</button>
    </div>
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

<style scoped>
.wrapper {
  display: flex;
  flex-direction: column;
}

.tag {
  display: flex;
  align-items: baseline;
}

.tag > span {
  width: 75%;
}

.tag-input {
  display: flex;
  align-items: baseline;
}

.tag-input > input[type="text"] {
  width: 75%;
}
</style>
