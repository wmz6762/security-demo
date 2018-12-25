<template>
  <div id="app" class="aui-wrapper aui-aside--white aui-header--colorful">
    <header class="aui-header">
      <div class="aui-header__hd">
        <a class="aui-brand aui-brand--lg" href="javascript:;">demo</a>
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
              <span>{{CurrentUser}}</span>
            </template>
            <!-- <el-menu-item index="3-1">修改密码</el-menu-item> -->
            <el-menu-item index="3-2" @click="logout">退出</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
    </header>

    <!-- aui-aside -->
    <aside class="aui-aside">
      <div class="aui-aside__inner">
        <el-menu
          v-if="asideMenuVisible"
          class="aui-aside__menu"
          :default-active="asideMenuActive"
          :collapse="asideFold"
          :unique-opened="true"
          :collapse-transition="false"
          :mode="asideTop ? 'horizontal' : 'vertical'"
        >
          <template v-for="(item, index) in CurrnetMenu">
            <el-menu-item :index="item.name" :key="index" @click="gotoPageHandle(item.path)">
              <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true">
                <use :href="item.icon"></use>
              </svg>
              <span slot="title">{{item.title}}</span>
            </el-menu-item>
          </template>
        </el-menu>
      </div>
    </aside>
    <main class="aui-main">
      <router-view/>
    </main>
  </div>
</template>

<script>
export default {
  name: "App",
  data: function() {
    return {
      asideMenuVisible: true,
      asideMenuActive: "home",
      asideFold: false,
      asideTop: false
    };
  },
  watch: {},
  created() {},
  methods: {
    gotoPageHandle: function(url) {
      this.$router.push({
        //核心语句
        path: url //跳转的路径
        // query:{           //路由传参时push和query搭配使用 ，作用时传递参数
        //   id:this.id ,
        // }
      });
    },
    logout() {
      this.$http({
        url: this.$http.adornUrl("/logout"),
        method: "get"
      }).then(({ data }) => {
        if (data === "ok") {
          this.$router.push({name:'login'})
        } else {
          alert(data);
        }
      });
    }
  },
  computed: {
    CurrnetMenu: {
      get() {
        return this.$store.state.user.menus;
      }
    },
    CurrentUser: {
      get() {
        return this.$store.state.user.name;
      }
    }
  }
};
</script>
<style>
</style>




