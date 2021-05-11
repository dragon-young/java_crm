<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">系统管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>员工管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>员工管理</span>
            </div>
            <el-row>
                <el-input placeholder="请输入姓名/邮箱" v-model="keyscontent" class="input-with-select" style="margin-top: 15px;width:500px">
                    <el-select v-model="selectcontent" slot="prepend" placeholder="全部" style="width:120px">
                        <el-option
                          v-for="(item, index) in options"
                          :key="index"
                          :label="item.name"
                          :value="item.id">
                        </el-option>
                    </el-select>
                    <el-button slot="append" icon="el-icon-search" @click="keySearch"></el-button>
                </el-input>
                <el-button type="primary" icon="el-icon-search" @click="keySearch">查询</el-button>
                <el-button type="success" icon="el-icon-circle-plus" @click="addEmployeeBefore">添加</el-button>
                <el-button type="danger" icon="el-icon-delete" @click="deleteManyEmployee">批量删除</el-button>
                <el-button type="warning" icon="el-icon-info" @click="exportEmployee">导出</el-button>
                <el-button type="warning" icon="el-icon-info" @click="importEmployee">导入</el-button>
            </el-row>
            <el-table id="out-table"
                :data="tableData"
                @selection-change="handleSelectionChange"
                stripe
                style="width: 100%">
                 <el-table-column
                type="selection"
                width="55">
                </el-table-column>
                <el-table-column
                prop="id"
                label="编号"
                width="180">
                </el-table-column>
                <el-table-column
                prop="name"
                label="名称"
                width="180">
                </el-table-column>
                <el-table-column
                prop="email"
                label="email">
                </el-table-column>
                <el-table-column
                prop="age"
                label="年龄">
                </el-table-column>
                <el-table-column
                prop="department.name"
                label="部门">
                </el-table-column>
                <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="editEmployee(scope.row)" icon="el-icon-edit" size="small" type="primary" round>编辑</el-button>
                    <el-button size="small" icon="el-icon-delete" type="danger" round @click="deleteEmployee(scope.row.id)">删除</el-button>
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
        <!-- 新增对话框 -->
        <el-dialog
          title="新增员工"
          :visible.sync="addEmployeeDialog"
          width="50%">
          <el-form :model="addEmployeeForm" ref="employeeForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名：">
                    <el-input v-model="addEmployeeForm.name"></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱：">
                    <el-input v-model="addEmployeeForm.email"></el-input>
                </el-form-item>
                <el-form-item label="年龄：">
                    <el-input v-model="addEmployeeForm.age"></el-input>
                </el-form-item>
                <el-form-item label="部门：">
                    <el-select v-model="addEmployeeForm.deptId" placeholder="请选择部门">
                        <el-option
                          v-for="item in departments"
                          :key="item.id"
                          :label="item.name"
                          :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态：">
                    <el-radio v-model="addEmployeeForm.state" label="1">正常</el-radio>
                    <el-radio v-model="addEmployeeForm.state" label="0">离职</el-radio>
                </el-form-item>
                <el-form-item label="超级管理员：">
                    <el-checkbox v-model="addEmployeeForm.admin">是否为管理员</el-checkbox>
                </el-form-item>
                <el-form-item label="分配角色：">
                    <el-transfer v-model="value" :data="addEmployeeForm.role"></el-transfer>
                </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="addEmployeeDialog = false">取 消</el-button>
            <el-button type="primary" @click="addEmployee">新增</el-button>
          </span>
        </el-dialog>
        <!-- 导入员工数据对话框 -->
        <el-dialog
          title="导入员工"
          :visible.sync="importEmployeeDialog"
          width="50%">

          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :file-list="fileList"
            ref="upload"
            :auto-upload="false"
            list-type="picture">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
            <div slot="tip" class="el-upload__tip">只能上传xsxl文件，且不超过500kb</div>
          </el-upload>
          <span slot="footer" class="dialog-footer">
            <el-button @click="importEmployeeDialog = false">取 消</el-button>
          </span>
        </el-dialog>
    </div>
</template>

<script>
import { export2Excel } from '../../common/js/util'
export default {
  data () {
    return {
      // 表头数据
      column: [{ title: '编号', key: 'id' }, { title: '名称', key: 'name' }, { title: 'email', key: 'email' }, { title: '年龄', key: 'age' }, { title: '部门', key: 'deptId' }],
      // 关键字内容
      keyscontent: '',
      // 下拉菜单
      selectcontent: '',
      currentPage4: 1,
      // 部门列表
      tableData: [],
      // 下拉部门选项
      options: [],
      // 分页信息
      pageNum: {
        currentPage: 1,
        pageSize: 4,
        totalCount: 0
      },
      // 新增员工对话框
      addEmployeeDialog: false,
      // 新增员工表单
      addEmployeeForm: {},
      departments: [],
      // 分配角色
      value: [1, 4],
      // 需要删除的项
      deleteItem: [],
      // 导入员工对话框
      importEmployeeDialog: false,
      // 需要导入的excel表格
      fileList: []
    }
  },
  mounted () {
    this.getEmployeeInfo()
  },
  methods: {
    editEmployee (scope) {
      this.$router.push({ path: '/sys/employee/edit', query: { scope: scope } })
    },
    async getEmployeeInfo () {
      const { data: res } = await this.$http.get(`employee/query?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}`)
      if (!res.success) return this.$message.error('获取员工信息失败')
      this.tableData = res.data.data
      this.pageNum = res.data
      for (var i = 0; i < res.data.data.length; i++) {
        this.options.push(res.data.data[i].department)
      }
      this.options = this.unique(this.options, 'id')
      // console.log(this.options)
    },
    async sizeChange (val) {
      // 页面发生改变调用方法
      const { data: res } = await this.$http.get(`employee/query?currentPage=${this.pageNum.currentPage}&pageSize=${val}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    async currentChange (val) {
      // 当前页发生改变是调用此方法
      const { data: res } = await this.$http.get(`employee/query?currentPage=${val}&pageSize=${this.pageNum.pageSize}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    // 数组去重方法
    unique (arr, attr) {
      const res = new Map()
      return arr.filter((item) => {
        var attrItem = item[attr]
        return !res.has(attrItem) && res.set(attrItem, 1)
      })
    },
    async deleteEmployee (id) {
      const { data: res } = await this.$http.get(`employee/delete?id=${id}`)
      if (!res.success) return this.$message.error('删除员工失败')
      this.getEmployeeInfo()
    },
    // 添加部门之前需要做的事情
    async addEmployeeBefore () {
      this.addEmployeeDialog = true
      const { data: res } = await this.$http.get('department/selectAll')
      this.departments = res.data
    },
    // 提交数据的事情
    async addEmployee () {
      await this.$http.get(`employee/input?name=${this.addEmployeeForm.name}&email=${this.addEmployeeForm.email}
      &age=${this.addEmployeeForm.age}&deptId=${this.addEmployeeForm.deptId}&state=${this.addEmployeeForm.state}&admin=${this.addEmployeeForm.admin === true ? 1 : 0}`)
      this.getEmployeeInfo()
      this.addEmployeeDialog = false
    },
    // 将员工数据导出到Excel表中
    async exportEmployee () {
      const { data: res } = await this.$http.get('employee/selectAll')
      export2Excel(this.column, res.data)
    },
    async deleteManyEmployee () {
      if (this.deleteItem.length === 0) return this.$message.error('请选择要删除的列表')
      var ids = []
      for (var i = 0; i < this.deleteItem.length; i++) {
        ids.push(this.deleteItem[i].id)
      }
      await this.$http.get(`employee/deleteEmployees?ids=${ids}`)
      this.getEmployeeInfo()
    },
    handleSelectionChange (val) {
      this.deleteItem = val
    },
    // 将Excel表格上传到服务器
    importEmployee () {
      // 先弹出对话框
      this.importEmployeeDialog = true
    },
    // 上传至服务器
    submitUpload () {
      this.$refs.upload.submit()
    },
    // 关键字查询
    async keySearch () {
      // 获取输入的关键字和部门id
      console.log(this.keyscontent)
      console.log(this.selectcontent)
      const { data: res } = await this.$http.get(`employee/query?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}
      &keyword=${this.keyscontent}&deptId=${this.selectcontent}`)
      console.log(res)
      this.tableData = res.data.data
      this.pageNum = res.data
    }
  }
}
</script>

<style lang="less" scoped>
// 分页样式
.pagination-style{
    margin-top: 20px;
}
</style>
