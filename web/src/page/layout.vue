<template>
  <div id="app" class="aui-wrapper aui-aside--white aui-control--open aui-aside--dark  aui-header--colorful">
    <header class="aui-header">
      <div class="aui-header__hd">
        <a class="aui-brand aui-brand--lg" href="javascript:;">权限-认证</a>
        <a class="aui-brand aui-brand--sm" href="javascript:;">demo</a>
      </div>
      <div class="aui-header__bd">
        <!-- aui-header__menu left -->
        <el-menu class="aui-header__menu aui-header__menu--left" mode="horizontal"></el-menu>
        <!-- aui-header__menu right -->
        <el-menu class="aui-header__menu aui-header__menu--right" mode="horizontal">
          <el-submenu index="3" :popper-append-to-body="false">
            <template slot="title">
              <!-- <img class="aui-avatar" src="@@path/img/avatar.png"> -->
              <span>admin</span>
            </template>
            <el-menu-item index="3-1">修改密码</el-menu-item>
            <el-menu-item index="3-2">退出</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </header>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "App",
  data: function() {
    return {};
  },
  watch: {},
  created() {},
  methods: {}
};
</script>
<style>
</style>




