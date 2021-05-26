<template>
    <div>
        <!-- 面包屑 -->
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">数据字典</a></el-breadcrumb-item>
            <el-breadcrumb-item>数据字典管理明细</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
                <span>数据字典管理明细</span>
            </div>
            <el-row style="margin-top: 20px" :gutter="100">
                <el-col :span="6">
                  <el-menu
                    router
                    default-active="2"
                    class="el-menu-vertical-demo">
                    <el-menu-item-group>
                      <template slot="title">数据字典分组</template>
                      <el-menu-item :index="'/sys/dataTable/'+ item.id" v-for="item in dataDetails" :key="item.id">{{item.title}}</el-menu-item>
                    </el-menu-item-group>
                  </el-menu>
                </el-col>
                <el-col :span="18">
                <router-view></router-view>
                </el-col>
            </el-row>
        </el-card>
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
      dataDetails: []
    }
  },
  mounted () {
    // 获取字典明细的数据
    this.getDataDictionaryDetails()
  },
  methods: {
    async editData (id) {
      const { data: res } = await this.$http.get(`data/delete?id=${id}`)
      if (!res.success) return this.$message.error('返回数据失败')
    },
    async getDataDictionaryDetails () {
      const { data: res } = await this.$http.get('dictionary/selectAll')
      this.dataDetails = res.data
      console.log(this.dataDetails)
    },
    getIndex (index) {
      console.log(index)
    }
  }
}
</script>

<style lang="less" scoped>
.pagination-style{
    margin-top: 20px;
}
</style>
