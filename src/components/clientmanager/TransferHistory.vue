<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">客户历史</a></el-breadcrumb-item>
            <el-breadcrumb-item>移交历史管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>移交历史管理</span>
            </div>
            <el-row>
                <el-col :span="8">
                    <el-input placeholder="请输入内容" v-model="keyword" class="input-with-select" style="width: 500px">
                        <el-button slot="append" icon="el-icon-search" @click="keySearch"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="8" class="line">
                    移交时间：
                    <el-date-picker
                        v-model="transferTime"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                    </el-date-picker>
                </el-col>
            </el-row>
                  <el-table id="out-table"
                :data="pageNum.data"
                stripe
                style="width: 100%">
                <el-table-column
                prop="id"
                label="编号"
                width="100">
                </el-table-column>
                <el-table-column
                prop="customer.name"
                label="客户姓名"
                width="100">
                </el-table-column>
                <el-table-column
                prop="transTime"
                label="操作时间"
                width="100">
                </el-table-column>
                <el-table-column
                prop="transUser"
                label="操作人"
                width="100">
                </el-table-column>
                <el-table-column
                prop="oldSeller"
                label="旧营销人员"
                width="180">
                </el-table-column>
                <el-table-column
                prop="employee.name"
                label="新营销人员"
                width="180">
                </el-table-column>
                <el-table-column
                prop="transReason"
                label="移交原因">
                </el-table-column>
            </el-table>
            <div class="pagination-style">
                <el-pagination
                    @size-change="sizeChange"
                    @current-change="currentChange"
                    :current-page="pageNum.currentPage"
                    :page-sizes="[2, 4, 6, 8]"
                    :page-size="pageNum.pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="pageNum.totalCount">
                </el-pagination>
            </div>
        </el-card>
    </div>
</template>

<script>
import { formatTime } from '../../common/js/timeFormat.js'
export default {
  data () {
    return {
      // 获取分页数据
      pageNum: {
        data: [],
        currentPage: 1,
        pageSize: 8
      },
      // 关键字查询
      keyword: '',
      // 下拉菜单
      options: [],
      value: '',
      // 编辑客户对话框
      editClientDialog: false,
      // 编辑客户表单
      editClientForm: {},
      // 移交时间
      transferTime: ''
    }
  },
  mounted () {
    // 获取页面数据
    this.getTransferHistoryInfo()
  },
  methods: {
    // 获取分页数据
    async getTransferHistoryInfo () {
      const { data: res } = await this.$http.get(`transRecord/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}`)
      if (!res.success) return this.$message.error('获取员工信息失败')
      console.log(res)
      this.pageNum = res.data
    },
    async sizeChange (val) {
      // 页面发生改变调用方法
      const { data: res } = await this.$http.get(`transRecord/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${val}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    async currentChange (val) {
      // 当前页发生改变是调用此方法
      const { data: res } = await this.$http.get(`transRecord/queryForList?currentPage=${val}&pageSize=${this.pageNum.pageSize}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    async keySearch () {
      var startTime = formatTime(this.transferTime[0])
      var endTime = formatTime(this.transferTime[1])
      const { data: res } = await this.$http.get(`transRecord/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}
      &keyword=${this.keyword}&startTime=${startTime}&endTime=${endTime}`)
      this.pageNum = res.data
    },
    dateFormat (date) {
      return formatTime(date)
    }
  }
}
</script>

<style lang="less" scoped>
.pagination-style{
    margin-top: 20px;
}
.line {
    height: 40px;
    line-height: 40px;
}
</style>
