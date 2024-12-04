<script setup>
import Label from "@/atomic/Label.vue";
import InputBox from "@/atomic/InputBox.vue";
import {ref} from "vue";

const props = defineProps({
  isDark : {
    type : Boolean,
    default: false
  },
  label : {
    type : String,
    default: "",
  },
  inputType : {
    type: String,
    default: "text"
}
})

const isFocus = ref(false);

const blurHandler = (value) => {
  isFocus.value = value;
}

const focusHandler = (value) => {
  isFocus.value = value;
}

</script>

<template>
    <div :class="[{componentContainer : true}, {focus : isFocus===true},  {dark : isDark}]">
      <Label :content="label" width="8"/>
      <InputBox :input-type="inputType" @on-blur="blurHandler" @on-focus="focusHandler" />
    </div>
</template>

<style scoped>
  .componentContainer {
    display: flex;
    width: 90%;
    align-items: center;
    position: relative;
    flex-direction: row;
    border-bottom: 1px solid #e5e5e5;

  }
  .focus {
    border-bottom: 1px solid black;
  }

  .dark {
    border-bottom: 1px solid white;
    color: #fff;
  }

  .dark.focus {
    border-bottom: 1px solid white;
  }
</style>