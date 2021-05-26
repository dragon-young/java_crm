<template>
  <div>
    <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">报表统计</a></el-breadcrumb-item>
        <el-breadcrumb-item>潜在客户报表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>潜在客户报表</span>
        </div>
        <el-row>
            <el-col>
                <el-date-picker
                v-model="timeLimit"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
                </el-date-picker>
                <el-select v-model="type" placeholder="请选择">
                    <el-option label="年" value="1"></el-option>
                    <el-option label="月" value="2"></el-option>
                    <el-option label="日" value="3"></el-option>
                    <el-option label="员工" value="4"></el-option>
                </el-select>
                <el-button @click="search">查询</el-button>
            </el-col>
        </el-row>
        <el-table
            :data="tableData"
            style="width: 100%">
            <el-table-column
            prop="type"
            label="分组类型"
            width="180">
            </el-table-column>
            <el-table-column
            prop="num"
            label="潜在客户新增数"
            width="180">
            </el-table-column>
        </el-table>
        <el-row>
            <el-col :span="12">
                <div id="main" style="width: 600px;height:400px;"></div>
            </el-col>
            <el-col :span="12">
                <div id="main02" style="width: 600px;height:400px;"></div>
            </el-col>
        </el-row>
    </el-card>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import { formatTime } from '../../common/js/timeFormat.js'
export default {
  data () {
    return {
      timeLimit: '',
      type: '',
      tableData: []
    }
  },
  mounted () {
    this.search()
  },
  methods: {
    async search () {
      if (this.type === '') {
        return this.$message.info('请选择查询条件')
      }
      var startTime = formatTime(this.timeLimit[0])
      var endTime = formatTime(this.timeLimit[1])
      const { data: res } = await this.$http.get(`customer/selectStatement?startTime=${startTime}&endTime=${endTime}&type=${this.type}`)
      this.tableData = res.data
      this.drawPic()
      this.drawPic2()
    },
    drawPic () {
      var myChart = echarts.init(document.getElementById('main'))
      var xdata = []
      var ydata = []
      for (var i = 0; i < this.tableData.length; i++) {
        xdata.push(this.tableData[i].type)
        ydata.push(this.tableData[i].num)
      }
      var name = ''
      console.log(this.type)
      if (this.type === '1') {
        name = '年'
      } else if (this.type === '2') {
        name = '月'
      } else if (this.type === '3') {
        name = '日'
      } else {
        name = '员工'
      }
      var option = {
        title: {
          text: '潜在客户数据'
        },
        tooltip: {},
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: { readOnly: false },
            magicType: { type: ['line', 'bar'] },
            restore: {},
            saveAsImage: {}
          }
        },
        legend: {
          data: ['年', '月', '日']
        },
        xAxis: {
          data: xdata
        },
        yAxis: {
        },
        series: [{
          name: name,
          type: 'bar',
          data: ydata
        }]
      }
      myChart.setOption(option)
    },
    drawPic2 () {
      var myChart = echarts.init(document.getElementById('main02'))
      var data = []
      for (var i = 0; i < this.tableData.length; i++) {
        data.push({ value: this.tableData[i].num, name: this.tableData[i].type })
      }
      var name = ''
      console.log(this.type)
      if (this.type === '1') {
        name = '年'
      } else if (this.type === '2') {
        name = '月'
      } else if (this.type === '3') {
        name = '日'
      } else {
        name = '员工'
      }
      var option = {
        title: {
          text: '潜在客户报表',
          subtext: name,
          left: 'center'
        },
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: { readOnly: false },
            magicType: { type: ['line', 'bar'] },
            restore: {},
            saveAsImage: {}
          }
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center'
        },
        series: [{
          type: 'pie',
          radius: '50%',
          selectedMode: 'single',
          data: data
        }]
      }
      myChart.setOption(option)
    }
  }
}
</script>

<style>

</style>
