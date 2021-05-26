<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">客户管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>正式客户管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>正式客户管理</span>
            </div>
            <el-row>
                <el-col :span="8">
                    <el-input placeholder="请输入姓名或者电话" v-model="keyword" class="input-with-select" style="width: 500px">
                        <el-select v-model="status" placeholder="请选择" slot="prepend" style="width:150px">
                            <el-option label="全部" value="null"></el-option>
                            <el-option label="流失" value="-2"></el-option>
                            <el-option label="正式客户" value="1"></el-option>
                        </el-select>
                        <el-button slot="append" icon="el-icon-search" @click="keySearch"></el-button>
                    </el-input>
                </el-col>
                <el-col :span="6">
                    <el-button type="success" @click="addClientBefore">添加客户</el-button>
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
                prop="name"
                label="姓名"
                width="100">
                </el-table-column>
                <el-table-column
                prop="age"
                label="年龄"
                width="100">
                </el-table-column>
                <el-table-column
                prop="gender"
                label="性别"
                width="100">
                  <template slot-scope="scope">
                    {{scope.row.gender === 0 ? '男' : '女' }}
                  </template>
                </el-table-column>
                <el-table-column
                prop="tel"
                label="电话"
                width="180">
                </el-table-column>
                <el-table-column
                prop="qq"
                label="qq"
                width="180">
                </el-table-column>
                <el-table-column
                label="职位"
                width="100">
                  <template slot-scope="scope">
                    {{ position(scope.row.jobId) }}
                  </template>
                </el-table-column>
                <el-table-column
                label="来源"
                width="180">
                <template slot-scope="scope">
                    {{ source(scope.row.sourceId) }}
                  </template>
                </el-table-column>
                <el-table-column
                prop="employee.name"
                label="营销人员"
                width="100">
                </el-table-column>
                <el-table-column
                prop="status"
                label="状态"
                width="100">
                  <template slot-scope="scope">
                    {{ statusByid(scope.row.status) }}
                  </template>
                </el-table-column>
                <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-button @click="editClientBefore(scope.row)" icon="el-icon-edit" size="small" type="primary" round>编辑</el-button>
                    <el-button @click="follwUpBefore(scope.row)" icon="el-icon-edit" size="small" type="primary" round plain>跟进</el-button>
                    <el-button @click="transferBefore(scope.row)" icon="el-icon-edit" size="small" type="warning" round>移交</el-button>
                    <el-button @click="editStatusBefore(scope.row)" icon="el-icon-edit" size="small" type="danger" round>修改状态</el-button>
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
                    <el-input v-model="editClientForm.age" placeholder="请输入角色名称"></el-input>
                </el-form-item>
                <el-form-item label="性别：">
                    <el-select v-model="editClientForm.gender" placeholder="请选择活动区域">
                      <el-option label="男" :value="0"></el-option>
                      <el-option label="女" :value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电话：">
                    <el-input v-model="editClientForm.tel" placeholder="请输入角色编号"></el-input>
                </el-form-item>
                <el-form-item label="qq：">
                    <el-input v-model="editClientForm.qq" placeholder="请输入角色编号"></el-input>
                </el-form-item>
                <el-form-item label="职位：">
                    <el-select v-model="editClientForm.jobId">
                      <el-option
                        v-for="item in dataDetails"
                        :key="item.id"
                        :label="item.title"
                        :value="item.id">
                      </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="来源：">
                    <el-select v-model="editClientForm.sourceId" placeholder="请选择活动区域">
                      <el-option
                        v-for="item in sourceInfo"
                        :key="item.id"
                        :label="item.title"
                        :value="item.id">
                      </el-option>
                    </el-select>
                </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="editClientDialog = false">取 消</el-button>
            <el-button type="primary" @click="editClientInfo">编辑</el-button>
          </span>
        </el-dialog>
        <!-- 新增客户对话框 -->
        <el-dialog
          title="新增客户"
          :visible.sync="addClientDialog"
          width="50%">
          <el-form :model="addClientForm" ref="editClientFormRefs" label-width="100px" class="demo-ruleForm">
                <el-form-item label="姓名：">
                    <el-input v-model="addClientForm.name" placeholder="请输入角色名称"></el-input>
                </el-form-item>
                <el-form-item label="年龄：">
                    <el-input v-model="addClientForm.age" placeholder="请输入角色名称"></el-input>
                </el-form-item>
                <el-form-item label="性别：">
                    <el-select v-model="addClientForm.gender" placeholder="请选择活动区域">
                      <el-option label="男" value="0"></el-option>
                      <el-option label="女" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电话：">
                    <el-input v-model="addClientForm.tel" placeholder="请输入角色编号"></el-input>
                </el-form-item>
                <el-form-item label="qq：">
                    <el-input v-model="addClientForm.qq" placeholder="请输入角色编号"></el-input>
                </el-form-item>
                <el-form-item label="职位：">
                    <el-select v-model="addClientForm.jobId" placeholder="请选择职位">
                      <el-option :label="item.title" :value="item.id" v-for="item in dataDetails" :key="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="来源：">
                    <el-select v-model="addClientForm.sourceId" placeholder="请选择来源">
                      <el-option :label="item.title" :value="item.id" v-for="item in sourceInfo" :key="item.id"></el-option>
                    </el-select>
                </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="addClientDialog = false">取 消</el-button>
            <el-button type="primary" @click="addClientInfo">添加</el-button>
          </span>
        </el-dialog>
        <!-- 跟进记录对话框 -->
        <el-dialog
          title="跟进记录"
          :visible.sync="followUpDialog"
          width="50%">
          <el-form :model="followUpForm" ref="followUpRefs" label-width="100px" class="demo-ruleForm">
                <el-form-item label="客户姓名：">
                    <el-input v-model="followUpForm.name" placeholder="客户姓名" disabled></el-input>
                </el-form-item>
                <el-form-item label="跟进时间：">
                    <el-date-picker
                      v-model="followUpForm.traceTime"
                      type="date"
                      placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="跟进内容：">
                    <el-input type="textarea" v-model="followUpForm.traceDetails" placeholder="跟进内容"></el-input>
                </el-form-item>
                <el-form-item label="跟进方式：">
                    <el-select v-model="followUpForm.traceTypeId" placeholder="跟进方式">
                      <el-option
                        v-for="item in followData"
                        :key="item.id"
                        :label="item.title"
                        :value="item.id">
                      </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="跟进结果：">
                    <el-select v-model="followUpForm.traceResult" placeholder="跟进结果">
                      <el-option label="优" value="3"></el-option>
                      <el-option label="中" value="2"></el-option>
                      <el-option label="差" value="1"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" :rows="2" v-model="followUpForm.remarks" placeholder="备注"></el-input>
                </el-form-item>
                <el-form-item label="跟进类型：">
                    <el-select v-model="followUpForm.type">
                      <el-option label="潜在客户开发" value="0"></el-option>
                      <el-option label="正式客户开发" value="1"></el-option>
                    </el-select>
                </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="followUpDialog = false">取 消</el-button>
            <el-button type="primary" @click="followUpInfo">保存</el-button>
          </span>
        </el-dialog>
        <!-- 移交记录对话框 -->
        <el-dialog
          title="跟进记录"
          :visible.sync="transferDailog"
          width="50%">
          <el-form :model="transferForm" ref="transferRefs" label-width="100px" class="demo-ruleForm">
                <el-form-item label="客户姓名：">
                    <el-input v-model="transferForm.name" placeholder="请输入角色名称" disabled></el-input>
                </el-form-item>
                <el-form-item label="旧营销人员：">
                    <el-input v-model="transferForm.employee.name" placeholder="请输入角色名称" disabled></el-input>
                </el-form-item>
                <el-form-item label="新营销人员：">
                    <el-select v-model="transferForm.newSeller" placeholder="请选择活动区域">
                      <el-option v-for="item in sellerData" :key="item.id" :label="item.name" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="移交原因：">
                    <el-input type="textarea" :rows="2" v-model="transferForm.transReason" placeholder="请输入角色编号"></el-input>
                </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="transferDailog = false">取 消</el-button>
            <el-button type="primary" @click="transferInfo">保存</el-button>
          </span>
        </el-dialog>
        <!-- 编辑客户状态对话框 -->
        <el-dialog
          title="编辑客户状态"
          :visible.sync="editStatusDailog"
          width="50%">
          <el-form :model="editStatusForm" ref="editStatusRefs" label-width="100px" class="demo-ruleForm">
                <el-form-item label="姓名">
                    <el-input v-model="editStatusForm.name" placeholder="请输入姓名名称"></el-input>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="editStatusForm.status" placeholder="请选择状态">
                            <el-option label="流失" value="-2"></el-option>
                            <el-option label="开发失败" value="-1"></el-option>
                            <el-option label="潜在客户" value="0"></el-option>
                            <el-option label="正式客户" value="1"></el-option>
                            <el-option label="资源池客户" value="2"></el-option>
                    </el-select>
                </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="editStatusDailog = false">取 消</el-button>
            <el-button type="primary" @click="editStatusInfo">保存</el-button>
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
        pageSize: 8
      },
      // 关键字查询
      keyword: '',
      // 客户状态查询
      status: '',
      // 下拉菜单
      options: [],
      value: '',
      // 编辑客户对话框
      editClientDialog: false,
      // 新增客户对话框
      addClientDialog: false,
      // 跟进记录对话框
      followUpDialog: false,
      // 移交人员对话框
      transferDailog: false,
      // 修改客户状态对话框
      editStatusDailog: false,
      // 编辑客户表单
      editClientForm: {},
      // 新增客户表单
      addClientForm: {},
      // 跟进记录表单
      followUpForm: {},
      // 移交人员表单
      transferForm: {
        employee: {
          name: ''
        }
      },
      // 修改客户状态表单
      editStatusForm: {},
      // 职位信息
      dataDetails: [],
      // 来源信息
      sourceInfo: [],
      // 新营销人员信息
      sellerData: [],
      // 跟进数据
      followData: []
    }
  },
  mounted () {
    // 获取页面数据
    this.getPotentialClientInfo()
    // 获取所有职位数据
    this.getDataInfo()
    // 获取所有营销人员
    this.getSellerInfo()
    // 获取跟进方式的数据
    this.getFollowData()
  },
  methods: {
    // 获取分页数据
    async getPotentialClientInfo () {
      const { data: res } = await this.$http.get(`customer/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}&status=1`)
      if (!res.success) return this.$message.error('获取员工信息失败')
      console.log(res)
      this.pageNum = res.data
    },
    async sizeChange (val) {
      // 页面发生改变调用方法
      const { data: res } = await this.$http.get(`customer/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${val}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    async currentChange (val) {
      // 当前页发生改变是调用此方法
      const { data: res } = await this.$http.get(`customer/queryForList?currentPage=${val}&pageSize=${this.pageNum.pageSize}`)
      this.tableData = res.data.data
      this.pageNum = res.data
    },
    // 关键字查询
    async keySearch () {
      if (this.status === 'null') {
        const { data: res } = await this.$http.get(`customer/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}`)
        if (!res.success) return this.$message.error('查询失败')
        this.pageNum = res.data
      } else {
        const { data: res } = await this.$http.get(`customer/queryForList?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}
        &keyword=${this.keyword}&status=${this.status}`)
        if (!res.success) return this.$message.error('查询失败')
        this.pageNum = res.data
      }
    },
    // 获取职位信息
    async getDataInfo () {
      // 获取所有字典明细信息
      const { data: res } = await this.$http.get('dictionarydetails/selectQueryById?dictionaryId=1&pageSize=999')
      this.dataDetails = res.data.data
      const { data: res2 } = await this.$http.get('dictionarydetails/selectQueryById?dictionaryId=2&pageSize=999')
      this.sourceInfo = res2.data.data
      console.log(this.dataDetails)
      console.log(this.sourceInfo)
    },
    async editData (id) {
      const { data: res } = await this.$http.get(`data/delete?id=${id}`)
      if (!res.success) return this.$message.error('返回数据失败')
    },
    // 编辑客户之前做的事情
    editClientBefore (val) {
      this.editClientForm = val
      // 1. 打开对话框
      this.editClientDialog = true
      // 2. 获取数据字典的来源
    },
    // 编辑客户信息
    async editClientInfo () {
      var editInfo = {
        id: this.editClientForm.id,
        name: this.editClientForm.name,
        age: this.editClientForm.age,
        gender: this.editClientForm.gender,
        tel: this.editClientForm.tel,
        qq: this.editClientForm.qq,
        jobId: this.editClientForm.jobId,
        sourceId: this.editClientForm.sourceId
      }
      // 1. 提交之前检查表单验证，如果验证通过才执行下面方法
      await this.$http.get(`customer/update?${this.formateObjToParamStr(editInfo)}`)
      // 2. 调用编辑客户接口
      // 3. 关闭对话框， 调用获取客户接口
      this.editClientDialog = false
      this.getPotentialClientInfo()
      // 4. 清空表单数据和验证记录
    },
    // 添加客户之前
    addClientBefore () {
      // 1. 获取数据之前信息
      // 2. 打开对话框
      this.addClientDialog = true
    },
    // 添加客户信息
    async addClientInfo () {
      // 1. 提交之前检查表单验证，如果验证通过才执行下面方法
      // 2. 调用添加客户客户接口
      var inputUserId = window.sessionStorage.getItem('id')
      this.addClientForm.sellerId = inputUserId
      this.addClientForm.inputUserId = inputUserId
      this.addClientForm.status = 1
      await this.$http.get(`customer/insert?${this.formateObjToParamStr(this.addClientForm)}`)
      // 3. 关闭对话框， 调用获取客户接口
      this.addClientDialog = false
      this.getPotentialClientInfo()
      // 4. 清空表单数据和验证记录
      this.addClientForm = {}
    },
    // 跟进记录之前需要做的事情
    follwUpBefore (val) {
      this.followUpForm = {
        customerId: val.id,
        name: val.name
      }
      // 2. 打开对话框
      this.followUpDialog = true
    },
    // 移交客户之前做的事情
    transferBefore (val) {
      // 1. 获取信息
      this.transferForm = val
      // 2. 打开对话框
      this.transferDailog = true
    },
    editStatusBefore (val) {
      this.editStatusForm = {
        name: val.name,
        id: val.id,
        stauts: val.status
      }
      // 打开对话框
      this.editStatusDailog = true
      // 获取信息
      this.getPotentialClientInfo()
    },
    async followUpInfo () {
      var inputUserId = window.sessionStorage.getItem('id')
      this.followUpForm.inputUserId = inputUserId
      await this.$http.get(`traceRecord/insert?${this.formateObjToParamStr(this.followUpForm)}`)
      this.followUpDialog = false
    },
    async transferInfo () {
      var tranUser = window.sessionStorage.getItem('name')
      var transInfo = {
        customerId: this.transferForm.id,
        transUser: tranUser,
        oldSeller: this.transferForm.employee.name,
        newSellerId: this.transferForm.newSeller,
        transReason: this.transferForm.transReason
      }
      await this.$http.get(`transRecord/insert?${this.formateObjToParamStr(transInfo)}`)
      this.transferDailog = false
      this.getPotentialClientInfo()
    },
    async editStatusInfo () {
      await this.$http.get(`customer/updateStatus?${this.formateObjToParamStr(this.editStatusForm)}`)
      // 关闭对话框
      this.editStatusDailog = false
      this.getPotentialClientInfo()
    },
    filter (str) { // 特殊字符转义
      str += '' // 隐式转换
      str = str.replace(/%/g, '%25')
      str = str.replace(/\+/g, '%2B')
      str = str.replace(/ /g, '%20')
      str = str.replace(/\//g, '%2F')
      str = str.replace(/\?/g, '%3F')
      str = str.replace(/&/g, '%26')
      str = str.replace(/=/g, '%3D')
      str = str.replace(/#/g, '%23')
      return str
    },
    formateObjToParamStr (paramObj) {
      const sdata = []
      for (var attr in paramObj) {
        sdata.push(`${attr}=${this.filter(paramObj[attr])}`)
      }
      return sdata.join('&')
    },
    // 获取所有销售人员信息
    async getSellerInfo () {
      const { data: res } = await this.$http.get('employee/selectSeller')
      this.sellerData = res.data
      console.log(this.sellerData)
    },
    // 获取跟进方式数据
    async getFollowData () {
      const { data: res } = await this.$http.get('dictionarydetails/selectQueryById?dictionaryId=5&pageSize=999')
      this.followData = res.data.data
    }
  },
  computed: {
    position (id) {
      return (id) => {
        for (var i = 0; i < this.dataDetails.length; i++) {
          if (id === this.dataDetails[i].id) return this.dataDetails[i].title
        }
        return '未知职位'
      }
    },
    source (id) {
      return (id) => {
        for (var i = 0; i < this.sourceInfo.length; i++) {
          if (id === this.sourceInfo[i].id) return this.sourceInfo[i].title
        }
        return '未知来源'
      }
    },
    statusByid (status) {
      return (status) => {
        if (status === -2) return '流失'
        else if (status === -1) return '开发失败'
        else if (status === 0) return '潜在客户'
        else if (status === 1) return '正式客户'
        else if (status === 2) return '资源池客户'
        else return '傻逼客户'
      }
    }
  }
}
</script>

<style lang="less" scoped>
.pagination-style{
    margin-top: 20px;
}
</style>
