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
                    <el-select v-model="editEmployeeForm.department.name" placeholder="请选择部门">
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
                    <el-transfer v-model="value" :data="data"></el-transfer>
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
      value: [1, 4]
    }
  },
  mounted () {
    this.editEmployeeForm = this.$route.query.scope
    if (this.editEmployeeForm.admin === 1) {
      this.editEmployeeForm.admin = true
    } else {
      this.editEmployeeForm.admin = false
    }
    // 获取部门信息
    this.getDepartment()
  },
  methods: {
    // 保存数据信息
    async save () {
      // 13
      console.log(this.editEmployeeForm)
    },
    async getDepartment () {
      const { data: res } = await this.$http.get('department/selectAll')
      console.log(res)
      this.departments = res.data
    }
  }
}
</script>

<style lang="less" scoped>

</style>
