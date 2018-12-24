<template>
  <div v-cloak class="aui-page page-login">
    <div class="login-wrapper">
      <header class="aui-page-header">
        <h2 class="aui-brand">权限与认证</h2>
        <ul class="aui-intro">
          <li>spring security/social/boot for demo</li>
        </ul>
      </header>

      <main class="aui-page-main">
        <h3 class="page-title">登录</h3>
        <el-form
          :model="dataForm"
          :rules="dataRule"
          ref="dataForm"
          @keyup.enter.native="dataFormSubmitHandle()"
        >
          <el-form-item prop="username">
            <el-input v-model="dataForm.username" placeholder="用户名／手机号码">
              <span slot="prefix" class="el-input__icon">
                <svg class="icon-svg" aria-hidden="true">
                  <use xlink:href="#icon-user"></use>
                </svg>
              </span>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="dataForm.password" type="password" placeholder="密码">
              <span slot="prefix" class="el-input__icon">
                <svg class="icon-svg" aria-hidden="true">
                  <use xlink:href="#icon-lock"></use>
                </svg>
              </span>
            </el-input>
          </el-form-item>
          <el-form-item prop="captcha">
            <el-row :gutter="20">
              <el-col :span="14">
                <el-input v-model="dataForm.captcha" placeholder="验证码">
                  <span slot="prefix" class="el-input__icon">
                    <svg class="icon-svg" aria-hidden="true">
                      <use xlink:href="#icon-safetycertificate"></use>
                    </svg>
                  </span>
                </el-input>
              </el-col>
              <el-col :span="10" class="login-captcha">
                <img :src="dataForm.captchaPath" @click="getCaptcha()">
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item size="mini">
            <div class="base-line-height clearfix">
              <el-checkbox v-model="dataForm.rememb" class="f-left">记住密码</el-checkbox>
              <!-- <a href="./forget@@v2.html" class="f-right">忘记密码？</a> -->
            </div>
          </el-form-item>
          <el-form-item>
            <el-button class="w-100" type="primary" @click="dataFormSubmitHandle()">登录</el-button>
          </el-form-item>
        </el-form>
        <div class="aui-shortcut">
          <h4 class="aui-shortcut__title">
            <span>使用社交账号直接登录</span>
          </h4>
          <ul class="aui-shortcut__list">
            <li>
              <a href="http://127.0.0.1:8080/login/weibo" style="color: #ec5c58;">
                <svg class="icon-svg" aria-hidden="true">
                  <use xlink:href="#icon-weibo-circle-fill"></use>
                </svg>
              </a>
            </li>
          </ul>
        </div>
        <!-- <p class="login-guide">
          还没有账号？
          <a href="./register@@v2.html">立即注册</a>
        </p>-->
      </main>
    </div>
  </div>
</template>

<script>
import { getUUID } from "@/util/index";
export default {
  name: "index",
  data() {
    return {
      dataForm: {
        language: "zh-CN",
        username: "",
        password: "",
        captcha: "",
        captchaPath: "",
        rememb: false
      },
      dataRule: {
        username: [
          { required: true, message: "必填项不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "必填项不能为空", trigger: "blur" }
        ],
        captcha: [
          { required: true, message: "必填项不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getCaptcha();
  },
  methods: {
    getCaptcha() {
      this.uuid = getUUID();
      this.dataForm.captchaPath = this.$http.adornUrl(
        `/captcha.jpg?uuid=${this.uuid}`
      );
    },
    login() {
      this.$http({
        url: this.$http.adornUrl("/login"),
        method: "post",
        params: this.$http.adornParams({
          username: this.dataForm.username,
          password: this.dataForm.password,
          captcha: this.dataForm.captcha
        })
      }).then(res => {
        // if (res.data === "ok") this.$router.replace({ name: "home" });
        // else
          this.$router.push({name:"home"})
      });
    },

    dataFormSubmitHandle() {
      var $this=this
      this.$refs["dataForm"].validate(function(valid) {
        if (valid) {
            $this.login();
        }
      });
    }
  }
};
</script>

<style scoped>
</style>
