<template>
  <div>
    <button @click="login">三方登录</button>
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
    login() {
      this.$http({
        url: this.$http.adornUrl("/login/weibo"),
        method: "get"
      }).then(res => {
        console.log(...res);
      });
    }
  },
  created() {
  
  }
};
</script>

<style scoped>
</style>  
