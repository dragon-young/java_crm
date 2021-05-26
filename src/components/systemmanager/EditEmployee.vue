<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/' }">系统管理</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/sys/employee' }">员工管理</el-breadcrumb-item>
            <el-breadcrumb-item>员工编辑</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>员工编辑</span>
            </div>
            <el-form ref="editEmployeeForm" :model="editEmployeeForm" label-width="100px" style="width:700px">
                <el-form-item label="用户名：">
                    <el-input v-model="editEmployeeForm.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="电子邮箱：">
                    <el-input v-model="editEmployeeForm.email"></el-input>
                </el-form-item>
                <el-form-item label="年龄：">
                    <el-input v-model="editEmployeeForm.age"></el-input>
                </el-form-item>
                <el-form-item label="部门：">
                    <el-select v-model="editEmployeeForm.department.id" placeholder="请选择部门">
                        <el-option
                          v-for="item in departments"
                          :key="item.id"
                          :label="item.name"
                          :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="超级管理员">
                    <el-checkbox v-model="editEmployeeForm.admin">是否为管理员</el-checkbox>
                </el-form-item>
                <el-form-item label="分配角色">
                    <el-select v-model="editEmployeeForm.roleId" label="editEmployeeForm.role.name" placeholder="请选择">
                      <el-option
                        v-for="item in roleInfo"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                      </el-option>
                    </el-select>
                </el-form-item>
                <el-button @click="save">保存</el-button>
                <el-button>重置</el-button>
            </el-form>
        </el-card>
    </div>
</template>

<script>
export default {
  data () {
    return {
      editEmployeeForm: {
        department: {
          name: '全部'
        }
      },
      // 部门的下拉菜单
      departments: [],
      // 角色权限列表
      data: [],
      value: [1, 4],
      // 所有角色信息
      roleInfo: []
    }
  },
  mounted () {
    this.editEmployeeForm = this.$route.query.scope
    if (this.editEmployeeForm.admin === 1) {
      this.editEmployeeForm.admin = true
    } else {
      this.editEmployeeForm.admin = false
    }
    // 获取角色西信息
    this.getRoleInfo()
    // 获取部门信息
    this.getDepartment()
  },
  methods: {
    // 保存数据信息
    async save () {
      const { data: res } = await this.$http.get(`employee/update?id=${this.editEmployeeForm.id}&name=${this.editEmployeeForm.name}&age=${this.editEmployeeForm.age}
      &deptId=${this.editEmployeeForm.department.id}&admin=${this.editEmployeeForm.admin === true ? 1 : 0}&roleId=${this.editEmployeeForm.roleId}
      &email=${this.editEmployeeForm.email}`)
      console.log(res)
      if (!res.success) return this.$message.error('数据修改失败')
      this.$message.success('数据获取成功')
      this.$router.push('/sys/employee')
    },
    async getDepartment () {
      const { data: res } = await this.$http.get('department/selectAll')
      console.log(res)
      this.departments = res.data
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
    async getRoleInfo () {
      const { data: res } = await this.$http.get('role/selectAll')
      this.roleInfo = res.data
    }
  }
}
</script>

<style lang="less" scoped>

</style>
