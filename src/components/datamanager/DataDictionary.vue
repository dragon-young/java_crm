<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">数据字典</a></el-breadcrumb-item>
            <el-breadcrumb-item>数据字典管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
            <el-input placeholder="请输入内容" v-model="keyword" @change="keySearch" class="input-with-select" style="width: 500px">
                <span slot="prepend">关键字</span>
                <el-button slot="append" icon="el-icon-search" @click="keySearch"></el-button>
            </el-input>
            <el-button type="success" @click="addDictionaryDialog = true">添加数据字典</el-button>
            <div style="margin-top: 20px">
                <el-table id="out-table"
                :data="pageNum.data"
                stripe
                style="width: 100%">
                <el-table-column
                prop="id"
                label="#"
                width="180">
                </el-table-column>
                <el-table-column
                prop="title"
                label="名称"
                width="180">
                </el-table-column>
                <el-table-column
                prop="sn"
                label="编码"
                width="180">
                </el-table-column>
                <el-table-column
                prop="intro"
                label="简介">
                </el-table-column>
                <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="editData(scope.row)" icon="el-icon-edit" size="small" type="primary" round>修改</el-button>
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
            </div>
        </el-card>
        <!-- 添加数据字典对话框 -->
        <el-dialog
          title="添加数据字典"
          :visible.sync="addDictionaryDialog"
          width="50%">
          <el-form :model="addDictionaryForm" ref="addDictionFormRef" label-width="100px" class="demo-ruleForm">
              <el-form-item label="名称">
                <el-input v-model="addDictionaryForm.title"></el-input>
              </el-form-item>
              <el-form-item label="编号">
                <el-input v-model="addDictionaryForm.sn"></el-input>
              </el-form-item>
              <el-form-item label="简介">
                <el-input type="textarea" v-model="addDictionaryForm.intro"></el-input>
              </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="addDictionaryDialog = false">取 消</el-button>
            <el-button type="primary" @click="addDictionaryInfo">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 修改数据字典 -->
        <el-dialog
          title="修改数据字典"
          :visible.sync="editDictionaryDialog"
          width="50%">
          <el-form :model="editDictionaryForm" ref="editDictionaryForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="名称">
                <el-input v-model="editDictionaryForm.title"></el-input>
              </el-form-item>
              <el-form-item label="编号">
                <el-input v-model="editDictionaryForm.sn"></el-input>
              </el-form-item>
              <el-form-item label="简介">
                <el-input type="textarea" v-model="editDictionaryForm.intro"></el-input>
              </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="editDictionaryDialog = false">取 消</el-button>
            <el-button type="primary" @click="editDictionaryInfo">确 定</el-button>
          </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
  data () {
    return {
      // 获取分页数据
      pageNum: {
        data: [],
        currentPage: 1,
        pageSize: 4
      },
      // 关键字查询
      keyword: '',
      // 添加数据对话框
      addDictionaryDialog: false,
      // 修改字典对话框
      editDictionaryDialog: false,
      // 添加字典表单
      addDictionaryForm: {},
      // 修改字典表单
      editDictionaryForm: {}
    }
  },
  mounted () {
    // 获取页面数据
    this.getDataDictionaryInfo()
  },
  methods: {
    // 获取分页数据
    async getDataDictionaryInfo () {
      const { data: res } = await this.$http.get(`dictionary/query?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}`)
      if (!res.success) return this.$message.error('获取员工信息失败')
      this.pageNum = res.data
    },
    async sizeChange (val) {
      // 页面发生改变调用方法
      const { data: res } = await this.$http.get(`dictionary/query?currentPage=${this.pageNum.currentPage}&pageSize=${val}`)
      this.pageNum = res.data
    },
    async currentChange (val) {
      // 当前页发生改变是调用此方法
      const { data: res } = await this.$http.get(`dictionary/query?currentPage=${val}&pageSize=${this.pageNum.pageSize}`)
      this.pageNum = res.data
    },
    async editData (val) {
      // 对话框打开之前获取数据
      this.editDictionaryForm = val
      // 打开对话框
      this.editDictionaryDialog = true
    },
    async addDictionaryInfo () {
      const { data: res } = await this.$http.get(`dictionary/input?title=${this.addDictionaryForm.title}&sn=${this.addDictionaryForm.sn}
      &intro=${this.addDictionaryForm.intro}`)
      if (!res.success) return this.$message.error('获取字典目录失败')
      // 1. 清空表单
      this.addDictionaryForm = {}
      this.$refs.addDictionFormRef.resetFields()
      // 2. 关闭对话框
      this.addDictionaryDialog = false
      // 3. 调用获取数据
      this.getDataDictionaryInfo()
    },
    async editDictionaryInfo () {
      // 在修改之前需要做的事情
      // 点击修改做的事情
      const { data: res } = await this.$http.get(`dictionary/update?id=${this.editDictionaryForm.id}&title=${this.editDictionaryForm.title}&sn=${this.editDictionaryForm.sn}
      &intro=${this.editDictionaryForm.intro}`)
      if (!res.success) return this.$message.error('修改字典目录失败')
      // 1. 清空表单
      this.editDictionaryForm = {}
      this.$refs.editDictionaryForm.resetFields()
      // 2. 关闭对话框
      this.editDictionaryDialog = false
      // 3. 调用获取数据
      this.getDataDictionaryInfo()
    },
    // 关键字查询
    async keySearch () {
      const { data: res } = await this.$http.get(`dictionary/query?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}
      &keyword=${this.keyword}`)
      if (!res.success) return this.$message.error('获取数据失败')
      this.pageNum = res.data
    }
  }
}
</script>

<style lang="less" scoped>
.pagination-style{
    margin-top: 20px;
}
</style>
