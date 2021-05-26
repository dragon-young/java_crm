<template>
  <div>
            <el-input placeholder="请输入内容" v-model="keyword" class="input-with-select" style="width: 500px">
                <span slot="prepend">关键字</span>
                <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
            <el-button type="success" @click="addBefore">添加数据字典明细</el-button>
    <el-table id="out-table"
            :data="pageNum.data"
            stripe
            style="width: 100%">
            <el-table-column
            prop="title"
            label="名称"
            width="180">
            </el-table-column>
            <el-table-column
            prop="sequence"
            label="序号"
            width="180">
            </el-table-column>
            <el-table-column
            width="180"
            label="操作">
            <template slot-scope="scope">
                <el-button @click="editDataBefore(scope.row)" icon="el-icon-edit" size="small" type="primary" round>修改</el-button>
            </template>
            </el-table-column>
                </el-table>
        <!-- 修改对话框 -->
        <el-dialog
          title="修改数据字典明细"
          :visible.sync="editDictionaryDetailsDialog"
          width="50%">
          <el-form :model="editDictionaryDetailsForm" ref="editDictionaryForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="名称">
                <el-input v-model="editDictionaryDetailsForm.title"></el-input>
              </el-form-item>
              <el-form-item label="编号">
                <el-input v-model="editDictionaryDetailsForm.sequence"></el-input>
              </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="editDictionaryDetailsDialog = false">取 消</el-button>
            <el-button type="primary" @click="editDictionaryDetailsInfo">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 添加对话框 -->
        <el-dialog
          title="添加数据字典明细"
          :visible.sync="addDictionaryDetailsDialog"
          width="50%">
          <el-form :model="addDictionaryDetailsForm" ref="editDictionaryForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="名称">
                <el-input v-model="addDictionaryDetailsForm.title"></el-input>
              </el-form-item>
              <el-form-item label="编号">
                <el-input v-model="addDictionaryDetailsForm.sequence"></el-input>
              </el-form-item>
              <el-form-item label="数据字典组">
                <el-select v-model="addDictionaryDetailsForm.dictionaryId" placeholder="请选择">
                    <el-option
                    v-for="item in dataDetails"
                    :key="item.id"
                    :label="item.title"
                    :value="item.id">
                    </el-option>
                </el-select>
              </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="addDictionaryDetailsForm = false">取 消</el-button>
            <el-button type="primary" @click="addDictionaryDetailsInfo">确 定</el-button>
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
      // 参数id
      id: '',
      editDictionaryDetailsDialog: false,
      editDictionaryDetailsForm: {},
      keyword: '',
      addDictionaryDetailsDialog: false,
      addDictionaryDetailsForm: {},
      dataDetails: []
    }
  },
  mounted () {
    // console.log(this.$route.params)
    // this.getDetails()
    this.getDataDetailsGroups()
  },
  methods: {
    async getDataDetailsGroups () {
      const { data: res } = await this.$http.get('dictionary/selectAll')
      this.dataDetails = res.data
    },
    async getDetails () {
      const { data: res } = await this.$http.get(`dictionarydetails/selectQueryById?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}&dictionaryId=${this.id}`)
      this.pageNum = res.data
    },
    async sizeChange () {
      // 1. 页面大小
      const { data: res } = await this.$http.get(`dictionarydetails/selectQueryById?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}&dictionaryId=${this.id}`)
      this.pageNum = res.data
    },
    async currentChange () {
      // 当前页
      const { data: res } = await this.$http.get(`dictionarydetails/selectQueryById?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}&dictionaryId=${this.id}`)
      this.pageNum = res.data
    },
    editDataBefore (val) {
      this.editDictionaryDetailsForm = val
      this.editDictionaryDetailsDialog = true
    },
    async editDictionaryDetailsInfo () {
      var val = this.editDictionaryDetailsForm
      console.log(val)
      await this.$http.get(`dictionarydetails/update?id=${val.id}&title=${val.title}&sequence=${val.sequence}&dictionaryId=${val.dictionaryId}`)
      this.editDictionaryDetailsDialog = false
      this.editDictionaryDetailsForm = {}
      this.getDetails()
    },
    addBefore () {
      console.log(this.dataDetails)
      this.addDictionaryDetailsDialog = true
    },
    async addDictionaryDetailsInfo () {
      var val = this.addDictionaryDetailsForm
      await this.$http.get(`dictionarydetails/input?title=${val.title}&sequence=${val.sequence}&dictionaryId=${val.dictionaryId}`)
      this.addDictionaryDetailsDialog = false
    }
  },
  watch: {
    async $route (to, from) {
      this.id = to.params.id
      const { data: res } = await this.$http.get(`dictionarydetails/selectQueryById?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}&dictionaryId=${this.id}`)
      this.pageNum = res.data
    }
  }
}
</script>

<style>
.pagination-style{
    margin-top: 20px;
}
</style>
