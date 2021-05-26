<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">系统管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>角色管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>角色管理</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="beforeAddRole">添加角色</el-button>
            </div>
             <el-table
                :data="pageNum.data"
                stripe
                style="width: 100%">
                <el-table-column
                prop="id"
                label="编号"
                width="180">
                </el-table-column>
                <el-table-column
                prop="name"
                label="角色名称"
                width="180">
                </el-table-column>
                <el-table-column
                width="180"
                prop="sn"
                label="角色编号">
                </el-table-column>
                <el-table-column
                label="权限">
                  <template slot-scope="scope">
                      <div slot="reference" class="name-wrapper">
                        <el-tag
                          :key="tag.id"
                          v-for="tag in scope.row.permissions"
                          :disable-transitions="false"
                          @close="handleClose(tag)">
                          {{tag.name}}
                        </el-tag>
                      </div>
                  </template>
                </el-table-column>
                <el-table-column
                label="操作">
                    <template  slot-scope="scope">
                      <el-button icon="el-icon-edit" type="primary" size="mini" @click="editRole(scope.row)">编辑</el-button>
                      <el-button icon="el-icon-delete" type="danger" size="mini" @click="deleteRole(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination-style">
            <el-pagination style="display: none"
                @size-change="sizeChange"
                @current-change="currentChange"
                :current-page="pageNum.currentPage"
                :page-sizes="[2, 4, 6, 8, 16, 99]"
                :page-size="pageNum.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="pageNum.totalCount">
            </el-pagination>
        </div>
        </el-card>
        <!-- 角色添加 -->
        <el-dialog
          style="min-width: 750px"
          title="角色添加"
          :visible.sync="addRoleDialog"
          width="50%">
          <el-form :model="addRoleForm" ref="editRoleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="角色名称：">
                    <el-input v-model="addRoleForm.name" placeholder="请输入角色名称"></el-input>
                </el-form-item>
                <el-form-item label="角色编号：">
                    <el-input v-model="addRoleForm.sn" placeholder="请输入角色编号"></el-input>
                </el-form-item>
                <el-form-item label="权限：">
                    <el-transfer
                    :props="{ key: 'id', label: 'name' }"
                     v-model="addRoleForm.permissionData" :data="permissionData"></el-transfer>
                </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="addRoleDialog = false">取 消</el-button>
            <el-button type="primary" @click="addRole">添加</el-button>
          </span>
        </el-dialog>
        <!-- 角色编辑对话框 -->
        <el-dialog
          title="角色编辑"
          :visible.sync="editRoleDialog"
          width="50%">
          <el-form :model="editRoleForm" ref="editRoleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="角色名称：">
                    <el-input v-model="editRoleForm.name" placeholder="请输入角色名称"></el-input>
                </el-form-item>
                <el-form-item label="角色编号：">
                    <el-input v-model="editRoleForm.sn" placeholder="请输入角色编号"></el-input>
                </el-form-item>
                <el-form-item label="权限：">
                    <el-transfer
                    :props="{ key: 'id', label: 'name' }"
                    :left-default-checked="editRoleForm.permissions"
                    :right-default-checked="editRoleForm.permissions"
                    v-model="editRoleForm.editPermissions" :data="permissionData"></el-transfer>
                </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="editRoleDialog = false">取 消</el-button>
            <el-button type="primary" @click="editRoleInfo">编辑</el-button>
          </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
  data () {
    return {
      pageNum: {
        data: [],
        pageSize: 99,
        currentPage: 1
      },
      // 角色编辑对话框
      editRoleDialog: false,
      // 角色编辑表单
      editRoleForm: {},
      // 添加角色表单
      addRoleForm: {
        permissionData: []
      },
      // 添加角色对话框
      addRoleDialog: false,
      permissionData: []
    }
  },
  mounted () {
    this.getAuthorityInfo()
  },
  methods: {
    async getAuthorityInfo () {
      const { data: res } = await this.$http.get(`role/query?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}`)
      this.pageNum = res.data
      console.log(this.pageNum)
    },
    async sizeChange (val) {
      const { data: res } = await this.$http.get(`role/query?currentPage=${this.pageNum.currentPage}&pageSize=${val}`)
      if (!res.success) return this.$message.error('获取信息失败')
      this.pageNum = res.data
    },
    async currentChange (val) {
      const { data: res } = await this.$http.get(`role/query?currentPage=${val}&pageSize=${this.pageNum.pageSize}`)
      if (!res.success) return this.$message.error('获取信息失败')
      this.pageNum = res.data
    },
    // 添加角色之前需要做的事情
    async beforeAddRole () {
      // 1. 调用权限数据
      const { data: res } = await this.$http.get('permission/select')
      this.permissionData = res.data
      // 2.打开对话框
      this.addRoleDialog = true
    },
    // 提交添加角色做的事情
    async addRole () {
      // 拿到提交的权限
      console.log(this.addRoleForm)
      const { data: res } = await this.$http.get(`role/input?sn=${this.addRoleForm.sn}&name=${this.addRoleForm.name}
      &permissions=${this.addRoleForm.permissionData}`)
      if (!res.success) return this.$message.error('添加数据失败')
      this.$message.success('添加数据成功')
      // 关闭对话框
      this.addRoleDialog = false
      this.getAuthorityInfo()
    },
    // 点击编辑角色拿到数据
    async editRole (val) {
      this.editRoleForm = val
      const { data: res } = await this.$http.get('permission/select')
      this.permissionData = res.data
      console.log(this.editRoleForm)
      console.log(this.editRoleForm.permissions)
      this.editRoleDialog = true
    },
    async editRoleInfo () {
      const { data: res } = await this.$http.get(`role/update?id=${this.editRoleForm.id}&sn=${this.editRoleForm.sn}&name=${this.editRoleForm.name}
      &permissions=${this.editRoleForm.editPermissions}`)
      if (!res.success) return this.$message.error('修改失败')
      this.$message.success('修改成功')
      // 关闭对话框
      this.editRoleDialog = false
      this.getAuthorityInfo()
    },
    async deleteRole (val) {
      var id = val.id
      const { data: res } = await this.$http.get(`role/delete?id=${id}`)
      if (!res.success) return this.$message.error('删除失败')
      this.$message.success('删除成功')
      this.getAuthorityInfo()
    }
  }
}
</script>

<style lang="less" scoped>
.pagination-style {
    margin-top: 20px;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>
