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
                <el-button style="float: right; padding: 3px 0" type="text" @click="reload">添加角色</el-button>
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
                prop="expression"
                label="角色编号">
                </el-table-column>
                <el-table-column
                label="操作">
                    <el-button icon="el-icon-edit" type="primary" size="mini">编辑</el-button>
                    <el-button icon="el-icon-delete" type="danger" size="mini">删除</el-button>
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
export default {
  data () {
    return {
      pageNum: {
        data: [],
        pageSize: 4,
        currentPage: 1
      }
    }
  },
  mounted () {
    this.getAuthorityInfo()
  },
  methods: {
    async getAuthorityInfo () {
      const { data: res } = await this.$http.get(`permission/query?currentPage=${this.pageNum.currentPage}&pageSize=${this.pageNum.pageSize}`)
      this.pageNum = res.data
      console.log(this.pageNum)
    },
    async sizeChange (val) {
      const { data: res } = await this.$http.get(`permission/query?currentPage=${this.pageNum.currentPage}&pageSize=${val}`)
      if (!res.success) return this.$message.error('获取信息失败')
      this.pageNum = res.data
    },
    async currentChange (val) {
      const { data: res } = await this.$http.get(`permission/query?currentPage=${val}&pageSize=${this.pageNum.pageSize}`)
      if (!res.success) return this.$message.error('获取信息失败')
      this.pageNum = res.data
    },
    // 重新加载
    async reload () {
      await this.$http.get('permission/reload')
      this.getAuthorityInfo()
    }
  }
}
</script>

<style lang="less" scoped>
.pagination-style {
    margin-top: 20px;
}
</style>
