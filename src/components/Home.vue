<template>
    <el-container>
        <!-- 头部代码 -->
        <el-header>
            <h3>客户管理系统</h3>
            <el-button class="home-container" style="width:70px;height:35px;vertical-align:center" @click="exit">退出</el-button>
        </el-header>
        <!-- 内容代码 -->
        <el-container>
            <!-- 左部区域 -->
            <el-aside width="200px">
                <div class="input-search">
                    <el-input
                        placeholder="search..."
                        suffix-icon="el-icon-search"
                        v-model="searchInput">
                    </el-input>
                </div>
                <el-menu
                    router
                    :default-active="defaultActive"
                    @select="handleSelect"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b">
                    <el-submenu index="/sys">
                        <template slot="title">
                            <i class="el-icon-s-home"></i>
                            <span>系统管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/sys/department">
                               <i class="el-icon-bangzhu"></i> 部门管理
                            </el-menu-item>
                            <el-menu-item index="/sys/employee">
                                <i class="el-icon-bangzhu"></i>员工管理
                            </el-menu-item>
                            <el-menu-item index="/sys/authority">
                                <i class="el-icon-bangzhu"></i>权限管理
                                </el-menu-item>
                            <el-menu-item index="/sys/role">
                                <i class="el-icon-bangzhu"></i>角色管理
                            </el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-s-data"></i>
                            <span>数据管理</span>
                        </template>
                        <el-menu-item-group>
                            <template slot="title">分组一</template>
                            <el-menu-item index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="分组2">
                            <el-menu-item index="1-3">选项3</el-menu-item>
                        </el-menu-item-group>
                        <el-submenu index="1-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="1-4-1">选项1</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-picture-outline-round"></i>
                            <span>客户管理</span>
                        </template>
                        <el-menu-item-group>
                            <template slot="title">分组一</template>
                            <el-menu-item index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="分组2">
                            <el-menu-item index="1-3">选项3</el-menu-item>
                        </el-menu-item-group>
                        <el-submenu index="1-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="1-4-1">选项1</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-bank-card"></i>
                            <span>客户历史</span>
                        </template>
                        <el-menu-item-group>
                            <template slot="title">分组一</template>
                            <el-menu-item index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="分组2">
                            <el-menu-item index="1-3">选项3</el-menu-item>
                        </el-menu-item-group>
                        <el-submenu index="1-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="1-4-1">选项1</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <el-submenu index="5">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>移交历史</span>
                        </template>
                        <el-menu-item-group>
                            <template slot="title">分组一</template>
                            <el-menu-item index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="分组2">
                            <el-menu-item index="1-3">选项3</el-menu-item>
                        </el-menu-item-group>
                        <el-submenu index="1-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="1-4-1">选项1</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                    <el-submenu index="6">
                        <template slot="title">
                            <i class="el-icon-document-copy"></i>
                            <span>报表统计</span>
                        </template>
                        <el-menu-item-group>
                            <template slot="title">分组一</template>
                            <el-menu-item index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                        </el-menu-item-group>
                        <el-menu-item-group title="分组2">
                            <el-menu-item index="1-3">选项3</el-menu-item>
                        </el-menu-item-group>
                        <el-submenu index="1-4">
                            <template slot="title">选项4</template>
                            <el-menu-item index="1-4-1">选项1</el-menu-item>
                        </el-submenu>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <!-- 内容区域 -->
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
export default {
  data () {
    return {
      defaultActive: 'charts',
      searchInput: '' // 搜索信息
    }
  },
  methods: {
    handleSelect (index) {
      this.defaultActive = index
    },
    exit () {
      window.sessionStorage.clear()
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="less" scoped>
.el-container {
    height: 100%;
}

.home-container {
    height: 100%;
}
.el-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    .el-button {
        line-height: 0;
    }
}
.el-aside {
    height: 100%;
    background-color: #545c64;
    overflow: hidden;
}
.el-menu {
    width: 100%;
}
// 左边搜索
.input-search{
    padding: 0 10px;
    margin: 5px 1px;
    margin-top: 10px;
    opacity: 0.5;
}
// 取消菜单栏子标题
</style>
