
<script setup>
  const props = defineProps({
    content: String,
    isDark: Boolean,
    fontSize: {
      type: Number,
      default: 1
    },
    fontWeight: {
      type: Number,
      default: 400
    },
    position: {
      type: String,
      default: "relative"
    },
    width: {
      type: String,
      default: "30px"
    }
  })
</script>

<template >
  <label :class="{dark : isDark}"
    :style="{ '--font-size': props.fontSize + 'rem', '--font-weight': props.fontWeight, '--position': props.position, '--width':width + 'rem' }"
  >{{props.content}}</label>
</template>

<style scoped>
  label {
    font-family: 'ptd';
    font-size: var(--font-size);
    font-weight: var(--font-weight);
    position: var(--position);
    width: var(--width);
  }

  .dark {
    color: white;
  }
</style>