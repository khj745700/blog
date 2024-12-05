<script setup>
  const emit = defineEmits(["onFocus", "onBlur"])
  const props = defineProps({
    inputType: {
      type: String,
      default: ''
    },

    isDark: {
      type: Boolean,
      default: false
    },
    value: {
      type: String
    },
    valueHandler: {
      type: Function
    },
  })

  const onFocus = (event) => {
    emit("onFocus", true);
  }

  const onBlur = (event) => {
    emit("onBlur", false);
  }
</script>

<template>
  <input :type="props.inputType"

         :value="value"
         @keyup="valueHandler"
         @focus="onFocus"
         @blur="onBlur"
  />
</template>

<style scoped>
  input {
    padding: 10px 10px;
    background-color: inherit;
    border: none;

    transition: border-bottom-color 0.2s;
    width: 100%;
    &:focus {
      outline: none;
    }
  }

</style>