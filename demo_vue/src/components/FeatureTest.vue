<template>
  <div id="app1">
    <h1>{{ title }}</h1>
    <p>链接：<a v-bind:href="urlBiDu">百度</a></p>
    <p>按钮点击次数：{{counter}}次</p>
    <button v-on:click="counter++">Add 1</button>
  </div>
</template>

<script>
export default {
  name: "FeatureTest",
  props: {
    title: String
  },
  data() {
    return {
      urlBiDu: "http://www.baidu.com",
      counter: 0
    }
  }
};
</script>

<style scoped>
#app1 {
  width: 100%;
}
</style>
