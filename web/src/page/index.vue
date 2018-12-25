<template>
  <div>
    <div class="aui-main__hd">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>首页</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <h3>{{getCurrnetUser}}</h3>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      user: ""
    };
  },
  methods: {

  },
  computed:{
    getCurrnetUser:{
       get () { return this.$store.state.user},
    }
  }
};
</script>

<style scoped>
</style>  
