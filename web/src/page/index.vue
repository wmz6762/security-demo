<template>
  <div>
    <h2>{{user}}</h2>
     <button @click="account">获取当前登录用户信息</button>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      user: "没有用户信息"
    };
  },
  methods: {
    account() {
      this.$http({
        url: this.$http.adornUrl("/account"),
        method: "get"
      }).then(res => {
        this.user=res.data
      });
    }
  }
};
</script>

<style scoped>
</style>  
