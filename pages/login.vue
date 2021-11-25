<template>
  <div class="main">
    <div class="title">
      <a class="active" href="/login">登录</a>
      <span>·</span>
      <a href="/register">注册</a>
    </div>

    <div class="sign-up-container">
      <el-form ref="userForm" :model="user">

        <el-form-item class="input-prepend restyle" prop="mobile" :rules="[{ required: true, message: '请输入邮箱地址', trigger: 'blur' },{validator: checkPhone, trigger: 'blur'}]">
          <div >
            <el-input type="text" placeholder="邮箱地址" v-model="user.mobile"/>
            <i class="iconfont icon-qq" />
          </div>
        </el-form-item>

        <el-form-item class="input-prepend" prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <div>
            <el-input type="password" placeholder="密码" v-model="user.password"/>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>

        <div class="btn">
          <input type="button" class="sign-in-button" value="登录" @click="submitLogins()">
        </div>
      </el-form>
      <!-- 更多登录方式 -->
      <div class="more-sign">
        <h6>微信登录</h6>
        <ul >
          <li><span id="weixin" class="weixin"  @click="longinByweixin()"><i class="iconfont icon-weixin"/></span></li>
          <!-- <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li> -->
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
  import '~/assets/css/sign.css'
  import '~/assets/css/iconfont.css'
  import cookie from 'js-cookie'

  import loginApi from '@/api/login'
  export default {
    layout: 'sign',

    data () {
      return {
        user:{
          mobile:'',
          password:''
        },
        loginInfo:{}
      }
    },

    methods: {
      longinByweixin(){
        loginApi.weixinLogin()
      },
      submitLogins(){
        // console.log(this.user)
        loginApi.submitLogin(this.user).then(response => {
            if(response.data.success){
                // console.log(response.data.data.token)

                //把token存在cookie中、也可以放在localStorage中
                cookie.set('guli_token', response.data.data.token, { domain: 'localhost' })
                // console.log(response.data.data.token)
                //登录成功根据token获取用户信息
                loginApi.getLoginInfo().then(response => {
                  
                  this.loginInfo = response.data.data.item
                  // console.log('======')
                  // console.log(this.loginInfo)
                  //将用户信息记录cookie
                  // cookie.set('guli_ucenter', this.loginInfo, { domain: 'localhost' })
                  cookie.set('id', this.loginInfo.id)
                  cookie.set('avatar', this.loginInfo.avatar)
                  cookie.set('nicknam', this.loginInfo.nicknam)
                  //跳转页面
                  window.location.href = "/";
                })
            }
          })
      },

      checkPhone (rule, value, callback) {
        // //debugger
        // if (!('[1-9]\d{7,10}@qq\.com'.test(value))) {
        //   return callback(new Error('邮箱地址格式不正确'))
        // }
        var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                if ( !regEmail.test(value)) {
                  return callback(new Error('邮箱地址格式不正确'))
                    // this.$message({
                    //     message: '邮箱格式不正确',
                    //     type: 'error'
                    // })
                    
                }
        return callback()
      }
    }
  }
</script>
<style>
   .el-form-item__error{
    z-index: 9999999;
  }
</style>
