<template>
    <div id="app1">
        <h1>{{ title }}</h1>
        <p>链接：<a v-bind:href="urlBiDu">百度</a></p>
        <p>按钮点击次数：{{counter}}次</p>
        <button v-on:click="counter++">Add 1</button>
        <p>{{message}}</p>
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
                counter: 0,
                message: ""
            }
        },
        mounted() {
            this.$axios.get('http://localhost:8080/api', {
                params: {}
            }).then(response => {
                this.message = "response:" + response.data;
            }).catch(error => {
                this.message = "error:" + error.message;
            });
        }
    };
</script>

<style scoped>
    #app1 {
        width: 100%;
    }
</style>
