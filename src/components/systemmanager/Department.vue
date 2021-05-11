<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">系统管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>部门管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>部门管理</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="addDepartmentDialog = true">添加部门</el-button>
            </div>
            <el-table
                :data="tableData"
                stripe
                style="width: 100%">
                <el-table-column
                prop="id"
                label="编号"
                width="180">
                </el-table-column>
                <el-table-column
                prop="name"
                label="部门名称"
                width="180">
                </el-table-column>
                <el-table-column
                prop="sn"
                label="部门编号">
                </el-table-column>
                <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="editDepartment(scope.row)" icon="el-icon-edit" size="small" type="primary" round>编辑</el-button>
                    <el-button size="small" icon="el-icon-delete" type="danger" round @click="deleteDepartment(scope.row)">删除</el-button>
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
        <!-- 弹出框 -->
        <el-dialog
          title="添加部门"
          :visible.sync="addDepartmentDialog"
          width="50%"
          :before-close="handleClose">
          <el-form :model="departmentForm" :rules="departmentRules" ref="departmentForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="部门名称" prop="name">
              <el-input v-model="departmentForm.name"></el-input>
            </el-form-item>
            <el-form-item label="部门编号" prop="sn">
              <el-input v-model="departmentForm.sn"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="addDepartmentDialog = false">取 消</el-button>
            <el-button type="primary" @click="addDepartment">添加</el-button>
          </span>
        </el-dialog>
        <!-- 弹出框 -->
        <el-dialog
          title="编辑部门"
          :visible.sync="editDepartmentDialog"
          width="50%"
          :before-close="handleClose">
          <el-form :model="departmentForm" :rules="departmentRules" ref="editDepartmentForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="编号" prop="id">
              <el-input v-model="departmentForm.id" disabled></el-input>
            </el-form-item>
            <el-form-item label="部门名称" prop="name">
              <el-input v-model="departmentForm.name"></el-input>
            </el-form-item>
            <el-form-item label="部门编号" prop="sn">
              <el-input v-model="departmentForm.sn"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="editDepartmentDialog = false">取 消</el-button>
            <el-button type="primary" @click="editDepartmentInfo">编辑</el-button>
          </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
  data () {
    var checkName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'))
      }
      setTimeout(async () => {
        const { data: res } = await this.$http.get(`department/select?name=${value}`)
        if (!res.success) callback(new Error('用户名以存在，请换一个用户名'))
      }, 1000)
    }
    return {
      departmentForm: {
        id: '',
        name: '',
        sn: ''
      },
      currentPage4: 1,
      tableData: [],
      // 分页数据
      pageNum: {
        totalCount: 0,
        currentPage: 1,
        pageSize: 4
      },
      // 添加部门对话框
      addDepartmentDialog: false,
      // 编辑部门对话框
      editDepartmentDialog: false,
      // 添加部门规则
      departmentRules: {
        name: [
          { required: true, message: '请输入部门名称', trigger: 'blur' },
          { validator: checkName, trigger: 'blur' }
        ],
        sn: [
          { required: true, message: '请输入部门编号', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    this.getDepartment()
  },
  methods: {
    async getDepartment () {
      const { data: res } = await this.$http.get(`department/query?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}`)
      console.log(res)
      if (!res.success) return this.$message.error('部门信息获取失败')
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    async deleteDepartment (scope) {
      const promiseResult = await this.$confirm('此操作将进行删除该信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(error => error)
      if (promiseResult !== 'confirm') return this.$message.info('你已经取消了该操作!!')
      var id = scope.id
      await this.$http.get(`department/delete?id=${id}`)
      this.$message.success('删除成功')
      this.getDepartment()
    },
    async addDepartment () {
      const promiseResult = await this.$confirm('此操作将进行编辑页面信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(error => error)
      if (promiseResult !== 'confirm') return this.$message.info('你已经取消了该操作!!')
      await this.$http.post(`department/input?name=${this.departmentForm.name}&sn=${this.departmentForm.sn}`)
      this.$message.success('添加数据成功')
      this.$refs.departmentForm.resetFields()
      // this.departmentForm.name = ''
      // this.departmentForm.sn = ''
      this.addDepartmentDialog = false
      this.getDepartment()
    },
    editDepartment (scope) {
      this.departmentForm = scope
      this.editDepartmentDialog = true
    },
    // 编辑部门信息
    async editDepartmentInfo () {
      const promiseResult = await this.$confirm('此操作将进行编辑页面信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(error => error)
      if (promiseResult !== 'confirm') return this.$message.info('你已经取消了该操作!!')
      await this.$http.post(`department/update?name=${this.departmentForm.name}&sn=${this.departmentForm.sn}`)
      this.$message.success('添加数据成功')
      this.$refs.departmentForm.resetFields()
      this.editDepartmentDialog = false
      this.getDepartment()
    },
    // 页面大小发生改变的时候
    async sizeChange (val) {
      const { data: res } = await this.$http.get(`department/query?currentPage=${this.pageNum.currentPage}&pageSize=${val}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    async currentChange (val) {
      const { data: res } = await this.$http.get(`department/query?currentPage=${val}&pageSize=${this.pageNum.pageSize}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    handleClose (done) {
      this.$refs.departmentForm.resetFields()
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    }
  }
}
</script>

<style lang="less" scoped>
// 分页样式
.pagination-style{
    text-algin: right;
    margin-top: 20px;
}
</style>
