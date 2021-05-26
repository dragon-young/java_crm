<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">客户历史</a></el-breadcrumb-item>
            <el-breadcrumb-item>跟进历史管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>跟进历史管理</span>
            </div>
            <el-row>
                <el-col :span="8">
                    <el-input placeholder="请输入内容" v-model="keyword" class="input-with-select" style="width: 500px">
                        <el-select v-model="value" placeholder="全部" slot="prepend" style="width:150px">
                          <el-option label="潜在开发计划" value="0"></el-option>
                          <el-option label="客户跟进历史" value="1"></el-option>
                        </el-select>
                    </el-input>
                </el-col>
                <el-col :span="8" class="line">
                    跟进时间：
                    <el-date-picker
                        v-model="followTime"
                        type="daterange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期">
                    </el-date-picker>
                </el-col>
                <el-col :span="4">
                  <el-button type="primary" icon="el-icon-search" @click="keySearch">查询</el-button>
                </el-col>
            </el-row>
                  <el-table id="out-table"
                :data="pageNum.data"
                stripe
                style="width: 100%">
                <el-table-column
                prop="id"
                label="编号"
                width="180">
                </el-table-column>
                <el-table-column
                prop="customer.name"
                label="姓名"
                width="180">
                </el-table-column>
                <el-table-column
                prop="traceTime"
                label="跟进时间"
                width="100">
                </el-table-column>
                <el-table-column
                prop="traceDetails"
                label="跟进内容">
                </el-table-column>
                <el-table-column
                label="跟进方式"
                width="180">
                  <template slot-scope="scope">
                    <el-tag effect="plain">{{followData(scope.row.traceTypeId)}}</el-tag>
                  </template>
                </el-table-column>
                <el-table-column
                prop="traceResult"
                label="跟进效果"
                width="180">
                  <template slot-scope="scope">
                    <el-tag effect="dark" :type="scope.row.traceResult === 3 ? 'success' : (scope.row.traceResult === 2 ? 'warning' : 'danger')">{{scope.row.traceResult === 3 ? '优' : (scope.row.traceResult === 2 ? '中' : '差')}}</el-tag>
                  </template>
                </el-table-column>
                <el-table-column
                prop="customerId"
                label="录入人"
                width="180">
                <template slot-scope="scope">
                    <el-tag effect="plain">{{seller(scope.row.customerId)}}</el-tag>
                  </template>
                </el-table-column>
                <el-table-column
                prop="type"
                label="跟进类型"
                width="180">
                  <template slot-scope="scope">
                    <el-tag>{{scope.row.type === 0 ? '潜在开发计划' : '客户跟进历史'}}</el-tag>
                  </template>
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
        <!-- 编辑客户对话框 -->
        <el-dialog
          title="客户编辑"
          :visible.sync="editClientDialog"
          width="50%">
          <el-form :model="editClientForm" ref="editClientFormRefs" label-width="100px" class="demo-ruleForm">
                <el-form-item label="姓名：">
                    <el-input v-model="editClientForm.name" placeholder="请输入角色名称"></el-input>
                </el-form-item>
                <el-form-item label="年龄：">
                    <el-input v-model="editClientForm.name" placeholder="请输入角色名称"></el-input>
                </el-form-item>
                <el-form-item label="性别：">
                    <el-select v-model="editClientForm.gender" placeholder="请选择活动区域">
                      <el-option label="男" value="0"></el-option>
                      <el-option label="女" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电话：">
                    <el-input v-model="editClientForm.sn" placeholder="请输入角色编号"></el-input>
                </el-form-item>
                <el-form-item label="qq：">
                    <el-input v-model="editClientForm.sn" placeholder="请输入角色编号"></el-input>
                </el-form-item>
                <el-form-item label="职位：">
                    <el-select v-model="editClientForm.gender" placeholder="请选择活动区域">
                      <el-option label="男" value="0"></el-option>
                      <el-option label="女" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="来源：">
                    <el-select v-model="editClientForm.gender" placeholder="请选择活动区域">
                      <el-option label="男" value="0"></el-option>
                      <el-option label="女" value="1"></el-option>
                    </el-select>
                </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="editClientDialog = false">取 消</el-button>
            <el-button type="primary" @click="editClientInfo">编辑</el-button>
          </span>
        </el-dialog>
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
      // 跟进时间
      followTime: '',
      // 跟进方式数据
      followWayData: [],
      // 录入人数据
      sellerData: []
    }
  },
  mounted () {
    // 获取跟进方式数据
    this.getfollowWayData()
    // 录入人
    this.getSellerData()
    // 获取页面数据
    this.getDataDictionaryInfo()
  },
  methods: {
    // 获取分页数据
    async getDataDictionaryInfo () {
      const { data: res } = await this.$http.get(`traceRecord/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}`)
      if (!res.success) return this.$message.error('获取员工信息失败')
      this.pageNum = res.data
    },
    async sizeChange (val) {
      // 页面发生改变调用方法
      const { data: res } = await this.$http.get(`traceRecord/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${val}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    async currentChange (val) {
      // 当前页发生改变是调用此方法
      const { data: res } = await this.$http.get(`traceRecord/queryForList?currentPage=${val}&pageSize=${this.pageNum.pageSize}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    async editData (id) {
      const { data: res } = await this.$http.get(`data/delete?id=${id}`)
      if (!res.success) return this.$message.error('返回数据失败')
    },
    // 编辑客户之前做的事情
    editClientBefore () {
      // 1. 打开对话框
      this.editClientDialog = true
      // 2. 获取数据字典的来源
    },
    // 编辑客户信息
    editClientInfo () {
      // 1. 提交之前检查表单验证，如果验证通过才执行下面方法
      // 2. 调用编辑客户接口
      // 3. 关闭对话框， 调用获取客户接口
      // 4. 清空表单数据和验证记录
    },
    async keySearch () {
      var startTime = formatTime(this.followTime[0])
      var endTime = formatTime(this.followTime[1])
      const { data: res } = await this.$http.get(`traceRecord/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}
      &keyword=${this.keyword}&startTime=${startTime}&endTime=${endTime}&type=${this.value}`)
      this.pageNum = res.data
    },
    async getfollowWayData () {
      const { data: res } = await this.$http.get('dictionarydetails/selectQueryById?dictionaryId=5&pageSize=999')
      this.followWayData = res.data.data
      console.log(this.followWayData)
    },
    async getSellerData () {
      const { data: res } = await this.$http.get('employee/selectAll')
      this.sellerData = res.data
      console.log(this.sellerData)
    }
  },
  computed: {
    followData (id) {
      return (id) => {
        for (var i = 0; i < this.followWayData.length; i++) {
          if (id === this.followWayData[i].id) return this.followWayData[i].title
        }
        return '神秘跟进方式'
      }
    },
    seller () {
      return (id) => {
        for (var i = 0; i < this.sellerData.length; i++) {
          if (id === this.sellerData[i].id) return this.sellerData[i].name
        }
        return '神秘录入人'
      }
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
