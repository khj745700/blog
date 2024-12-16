<script setup>

const props = defineProps({
  hashTags : {
    type: Array,
    default: []
  },
  hashTagClickEvent: {
    type: Function,
    default: () => {}
  }
})
</script>

<template>
  <div class="hashTagContainer">
    <span class="hashTags" v-for="hashtag in hashTags" key="hashtag" @click.prevent.self="hashTagClickEvent" :data-id="hashtag.hashtagId">
            #{{hashtag.hashtag}}
    </span>
  </div>

</template>

<style scoped>
.hashTags {
  border: 1px solid darkgoldenrod;
  border-radius: 8px;
  color: darkgoldenrod;
  display: inline-block;
  margin-right: 1rem;
  padding: 2px 5px;
  margin-bottom: 10px;
  font-weight: 400;
  font-size: 0.9rem;
  transition: background-color 0.1s ease-in-out, color 0.1s ease;

  &:hover {
    background-color: darkgoldenrod;
    color: white;
  }
}
</style>