<template>
  <div ref="chartDom" style="height: 100px;"></div>
</template>
<script>
  import echarts from "echarts";

  export default {
    name: "test",
    data() {
      return {
        charts: null,
        opinion: ["直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎"],
        opinionData: [
          {value: 335, name: "直接访问"},
          {value: 310, name: "邮件营销"},
          {value: 234, name: "联盟广告"},
          {value: 135, name: "视频广告"},
          {value: 1548, name: "搜索引擎"}
        ]
      };
    },
    methods: {
      drawPie() {
        this.charts = echarts.init(this.$refs.chartDom);
        this.charts.setOption({
          tooltip: {
            trigger: "item",
            formatter: "{a}<br/>{b}:{c} ({d}%)"
          },
          legend: {
            orient: "vertical",
            x: "left",
            data: this.opinion
          },
          series: [
            {
              name: "访问来源",
              type: "pie",
              radius: ["50%", "70%"],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: "center"
                },
                emphasis: {
                  show: true,
                  textStyle: {
                    fontSize: "30",
                    fontWeight: "blod"
                  }
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: this.opinionData
            }
          ]
        });
      }
    },
    mounted() {
      this.$nextTick(function () {
        this.drawPie();
      });
    }
  };
</script>

<style scoped></style>
