<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png" />
    <HelloWorld msg="Welcome to Your Vue.js App" />
    <FeatureTest title="测试用例" />
  </div>
</template>

<script>
import HelloWorld from "./components/HelloWorld.vue";
import FeatureTest from "./components/FeatureTest";

export default {
  name: "app",
  components: {
    FeatureTest,
    HelloWorld
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
