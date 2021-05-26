<template>
  <div class="container">
      <div class="box">
          <el-form :model="loginInfo" class="demo-ruleForm">
              <span class="login-form-title">登录</span>
              <el-form-item class="wrap-input">
                 <span class="label-input">用户名</span>
                 <el-input class="input100" v-model="loginInfo.name" placeholder="请输入用户名"></el-input>
              </el-form-item>
              <el-form-item class="wrap-input">
                <span class="label-input">密码</span>
                <el-input type="password" class="input100" v-model="loginInfo.password" placeholder="请输入密码"></el-input>
              </el-form-item>
              <el-form-item class="wrap-input">
                <span class="label-input" style="display:block">验证码</span>
                <el-input class="input100" style="width:200px" v-model="loginInfo.code" placeholder="请输入密码"></el-input>
                <el-image
                    style="width: 190px; height: 40px;"
                    :src="imgUrl" @click="getImg"></el-image>
              </el-form-item>
              <div class="loginBtn">
                  <el-button type="primary" style="width:200px; height: 40px" round @click="login">登陆</el-button>
              </div>
          </el-form>
      </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      loginInfo: {},
      imgUrl: 'http://192.168.137.1:8080/login/captcha'
    }
  },
  methods: {
    async login () {
      const { data: res } = await this.$http.get(`login/login?username=${this.loginInfo.name}&password=${this.loginInfo.password}&code=${this.loginInfo.code}`)
      if (!res.success) return this.$message.error('登陆失败')
      window.sessionStorage.setItem('id', res.data.info.id)
      window.sessionStorage.setItem('name', res.data.info.name)
      window.sessionStorage.setItem('token', res.data.token.value)
      this.$router.push('/sys')
    },
    getImg () {
      console.log(this.imgUrl)
      this.imgUrl = 'http://192.168.137.1:8080/login/captcha?a=' + new Date().getTime()
    }
  }
}
</script>

<style lang="less" scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
.container {
    width: 100%;
    height: 100%;
    background: url('../common/img/bg-01.jpg');
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: center;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
.box {
    width: 500px;
    background: #fff;
    border-radius: 10px;
    overflow: hidden;
    padding: 65px 55px 54px;
}
.login-form-title {
    display: block;
    font-size: 39px;
    color: #333;
    line-height: 1.2;
    text-align: center;
    padding-bottom: 49px;
}
.wrap-input {
    margin-bottom: 23px
}
.label-input {
    font-size: 14px;
    color: #333;
    line-height: 1.5;
    padding-left: 7px;
}
.el-input input {
    border: none;
    outline: none;
}
.el-input input:focus {
    border-color: transparent !important;
}

.input100 {
    // padding: 0 7px 0 43px;
    font-size: 16px;
    font-family: 'Microsoft Yahei';
}
.loginBtn {
    margin-top:30px;
    text-align: center;
}
</style>
